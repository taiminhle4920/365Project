package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcFindProfessorByGpa {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcFindProfessorByGpa() {
    }

    public void createTable(
            TableView<ProfessorGpa> table,
            TableColumn<ProfessorGpa, String> column1,
            TableColumn<ProfessorGpa, String> column2,
            TableColumn<ProfessorGpa, String> column3,
            TableColumn<ProfessorGpa, String> column4,
            TableColumn<ProfessorGpa, String> column5) {
        column1.setCellValueFactory(new PropertyValueFactory<>("pid"));
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

    public String findProfessorByGpa(
            double gpa,
            String major,
            TableView<ProfessorGpa> table) {
        try {
            table.getItems().clear();

            String sql = "SELECT * " +
            "FROM course " +
            "INNER JOIN grade on grade.cid = course.cid " +
            "INNER JOIN professor on course.pid = professor.pid " +
            "WHERE professor.major = ?;";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, major);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String pid = rs.getString("pid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String tempmajor = rs.getString("major");
                String tempgpa2 = "null";
                System.out.println(pid);
                System.out.println(firstName);
                System.out.println(lastName);



                table.getItems().add(new ProfessorGpa(pid, firstName, lastName, tempmajor, tempgpa2));
            }

            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}

