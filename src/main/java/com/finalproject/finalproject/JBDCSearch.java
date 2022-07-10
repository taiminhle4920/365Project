package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JBDCSearch {

    JDBCConnection jdbcConnection = new JDBCConnection();

    public JBDCSearch() {
    }

    public void createSearchStudentTable(TableView<Student> table1,
            TableColumn<Student, String> column1,
            TableColumn<Student, String> column2,
            TableColumn<Student, String> column3,
            TableColumn<Student, String> column4,
            TableColumn<Student, String> column5,
            TableColumn<Student, String> column6,
            TableView<Student> table2,
            TableColumn<Student, String> column7,
            TableColumn<Student, String> column8,
            TableColumn<Student, String> column9,
            TableColumn<Student, String> column10,
            TableColumn<Student, String> column11,
            TableColumn<Student, String> column12) {

        column1.setCellValueFactory(new PropertyValueFactory<>("sid"));
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column4.setCellValueFactory(new PropertyValueFactory<>("email"));
        column5.setCellValueFactory(new PropertyValueFactory<>("dob"));
        column6.setCellValueFactory(new PropertyValueFactory<>("major"));
        table1.getColumns().add(column1);
        table1.getColumns().add(column2);
        table1.getColumns().add(column3);
        table1.getColumns().add(column4);
        table1.getColumns().add(column5);
        table1.getColumns().add(column6);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        column7.setCellValueFactory(new PropertyValueFactory<>("cid"));
        column8.setCellValueFactory(new PropertyValueFactory<>("courseLabel"));
        column9.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        column10.setCellValueFactory(new PropertyValueFactory<>("pid"));
        column11.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        column12.setCellValueFactory(new PropertyValueFactory<>("schoolyear"));
        table2.getColumns().add(column7);
        table2.getColumns().add(column8);
        table2.getColumns().add(column9);
        table2.getColumns().add(column10);
        table2.getColumns().add(column11);
        table2.getColumns().add(column12);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public String searchStudent(
            String sid,
            TableView<Student> table1,
            TableView<Student> table2) {
        try {
            String sql = "SELECT * FROM student WHERE sid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(sid));
            ResultSet rs = preStatement.executeQuery();
            table1.getItems().clear();

            while (rs.next()) {
                int tempsid = rs.getInt("sid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String major = rs.getString("major");
                
                table1.getItems().add(new Student(tempsid, firstName, lastName, email, dob, major));
            }
            
            return null;
        } catch (SQLException e) {
            JDBCConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}