package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcSearchProfessor {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcSearchProfessor() {
    }

    public void createSearchProfessorTable(TableView<Professor> table1,
            TableColumn<Professor, String> column1,
            TableColumn<Professor, String> column2,
            TableColumn<Professor, String> column3,
            TableColumn<Professor, String> column4,
            TableColumn<Professor, String> column5,
            TableColumn<Professor, String> column6,
            TableView<ProfessorCourse> table2,
            TableColumn<ProfessorCourse, String> column7,
            TableColumn<ProfessorCourse, String> column8,
            TableColumn<ProfessorCourse, String> column9,
            TableColumn<ProfessorCourse, String> column10,
            TableColumn<ProfessorCourse, String> column11) {

        column1.setCellValueFactory(new PropertyValueFactory<>("pid"));
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
        column10.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        column11.setCellValueFactory(new PropertyValueFactory<>("schoolYear"));
        table2.getColumns().add(column7);
        table2.getColumns().add(column8);
        table2.getColumns().add(column9);
        table2.getColumns().add(column10);
        table2.getColumns().add(column11);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public String searchProfessor(
            String pid,
            TableView<Professor> table1,
            TableView<ProfessorCourse> table2) {
        try {
            table1.getItems().clear();
            table2.getItems().clear();

            String sql = "SELECT * FROM professor WHERE pid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, pid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String temppid = rs.getString("pid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String major = rs.getString("major");

                table1.getItems().add(new Professor(temppid, firstName, lastName, email, dob, major));
            }

            sql = "select distinct course.cid, course.courseLabel, course.courseName, course.quarter, course.schoolYear "
                    +
                    "from course " +
                    "where course.pid = ?;";

            this.jdbcConnection.makeConnection();
            connect = this.jdbcConnection.getConnection();
            preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(pid));
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String cid = rs.getString("cid");
                String courseLabel = rs.getString("courseLabel");
                String courseName = rs.getString("courseName");
                String quarter = rs.getString("quarter");
                String schoolYear = rs.getString("schoolYear");
                table2.getItems().add(new ProfessorCourse(cid, courseLabel, courseName, quarter, schoolYear));
            }

            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}
