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

    public void createTable(TableView<Table> table1,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4,
            TableColumn<Table, String> column5,
            TableColumn<Table, String> column6,
            TableView<Table> table2,
            TableColumn<Table, String> column7,
            TableColumn<Table, String> column8,
            TableColumn<Table, String> column9,
            TableColumn<Table, String> column10,
            TableColumn<Table, String> column11) {

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

    public String queryDataToTable(
            String pid,
            TableView<Table> table1,
            TableView<Table> table2) {
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
                String rspid = rs.getString("pid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable1 = new Table();
                newTable1.initSearchProfessorTable(rspid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table1.getItems().add(newTable1);
            }

            sql = "select distinct course.cid, course.courseLabel, course.courseName, course.quarter, course.schoolYear "
                    +
                    "from course " +
                    "where course.pid = ?;";

            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(pid));
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String rscid = rs.getString("cid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");
                Table newTable2 = new Table();
                newTable2.initSearchProfessorCourseTable(rscid, rscourseLabel, rscourseName, rsquarter, rsschoolYear);
                table2.getItems().add(newTable2);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}
