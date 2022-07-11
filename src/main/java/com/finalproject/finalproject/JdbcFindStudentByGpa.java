package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcFindStudentByGpa {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcFindStudentByGpa() {
    }

    public void createTable(
        TableView<StudentGpa> table,
        TableColumn<StudentGpa, String> column1,
        TableColumn<StudentGpa, String> column2,
        TableColumn<StudentGpa, String> column3,
        TableColumn<StudentGpa, String> column4,
        TableColumn<StudentGpa, String> column5
    ) {
        column1.setCellValueFactory(new PropertyValueFactory<>("sid"));
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column4.setCellValueFactory(new PropertyValueFactory<>("major"));
        column5.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // public String searchProfessor(
    //         String gpa,
    //         String major,
    //         TableView<StudentAvgGpa> table) {
    //     try {
    //         table.getItems().clear();

    //         String sql = "SELECT * FROM professor WHERE pid = ?;";
    //         this.jdbcConnection.makeConnection();
    //         Connection connect = this.jdbcConnection.getConnection();
    //         PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
    //         preStatement = connect.prepareStatement(sql);
    //         preStatement.setString(1, pid);
    //         ResultSet rs = preStatement.executeQuery();

    //         while (rs.next()) {
    //             String temppid = rs.getString("pid");
    //             String firstName = rs.getString("firstName");
    //             String lastName = rs.getString("lastName");
    //             String email = rs.getString("email");
    //             String dob = rs.getString("dob");

    //             table.getItems().add(new StudentAvgGpa());
    //         }

    //         return null;
    //     } catch (SQLException e) {
    //         JdbcConnection.printSQLException(e);
    //         return e.getMessage();
    //     }

    // }

}
