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
            TableView<Table> table,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4,
            TableColumn<Table, String> column5) {
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

    public String queryDataToTable(
            double gpa,
            String major,
            TableView<Table> table) {
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
                String rspid = rs.getString("pid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsmajor = rs.getString("major");
                String rsgpa = "null";

                Table newTable = new Table();
                newTable.initFindProfessorByClassGpa(rspid, rsfirstName, rslastName, rsmajor, rsgpa);
                table.getItems().add(newTable);
            }
            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

}
