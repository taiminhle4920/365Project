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
            TableColumn<StudentGpa, String> column5) {
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

    public String findStudentByGpa(
            double gpa,
            String major,
            TableView<StudentGpa> table) {
        try {
            table.getItems().clear();

            String sql1 = "SELECT student.sid, student.firstName, student.lastName, student.major, AVG(grade.grade) GPA "
                    +
                    "FROM grade " +
                    "INNER JOIN student on grade.sid = student.sid " +
                    "WHERE student.major = ? " +
                    "GROUP BY grade.sid " +
                    "HAVING AVG(grade.grade) > ? " +
                    "ORDER BY AVG(grade.grade) DESC;";

            String sql2 = "SELECT student.sid, student.firstName, student.lastName, student.major, AVG(grade.grade) GPA "
                    +
                    "FROM grade " +
                    "INNER JOIN student on grade.sid = student.sid " +
                    "GROUP BY grade.sid " +
                    "HAVING AVG(grade.grade) > ? " +
                    "ORDER BY AVG(grade.grade) DESC;";

            String tempgpa = String.valueOf(gpa);

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();

            if (major.equals("All")) {
                preStatement = connect.prepareStatement(sql2);
                preStatement.setString(1, tempgpa);
            } else {
                preStatement = connect.prepareStatement(sql1);
                preStatement.setString(1, major);
                preStatement.setString(2, tempgpa);
            }

            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                String sid = rs.getString("sid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String tempmajor = rs.getString("major");
                String tempgpa2 = rs.getString("gpa");

                table.getItems().add(new StudentGpa(sid, firstName, lastName, tempmajor, tempgpa2));
            }

            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}
