package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcQuery {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcQuery() {
    }

    public void createNewTable(
            TableView<Table> table,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4,
            TableColumn<Table, String> column5,
            TableColumn<Table, String> column6,
            String columnName1,
            String columnName2,
            String columnName3,
            String columnName4,
            String columnName5,
            String columnName6) {
        if (column1 != null) {
            column1.setCellValueFactory(new PropertyValueFactory<>(columnName1));
            table.getColumns().add(column1);
        }
        if (column2 != null) {
            column2.setCellValueFactory(new PropertyValueFactory<>(columnName2));
            table.getColumns().add(column2);
        }
        if (column3 != null) {
            column3.setCellValueFactory(new PropertyValueFactory<>(columnName3));
            table.getColumns().add(column3);
        }
        if (column4 != null) {
            column4.setCellValueFactory(new PropertyValueFactory<>(columnName4));
            table.getColumns().add(column4);
        }
        if (column5 != null) {
            column5.setCellValueFactory(new PropertyValueFactory<>(columnName5));
            table.getColumns().add(column5);
        }
        if (column6 != null) {
            column6.setCellValueFactory(new PropertyValueFactory<>(columnName6));
            table.getColumns().add(column6);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public String queryDataToSearchStudentTable(
            String sid,
            TableView<Table> table1,
            TableView<Table> table2) {
        try {
            table1.getItems().clear();
            table2.getItems().clear();

            String sql = "SELECT * FROM student WHERE sid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, sid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsemail = rs.getString("email");
                String rsdob = rs.getString("dob");
                String rsmajor = rs.getString("major");

                Table newTable1 = new Table();
                newTable1.initSearchStudentTable(rssid, rsfirstName, rslastName, rsemail, rsdob, rsmajor);
                table1.getItems().add(newTable1);
            }

            sql = "SELECT DISTINCT " +
                    "course.pid, course.courseLabel, course.courseName, course.quarter, course.schoolYear, grade.grade "
                    +
                    "FROM grade INNER JOIN course on grade.cid = course.cid " +
                    "WHERE grade.sid = ?;";

            preStatement = connect.prepareStatement(sql);
            preStatement.setString(1, sid);
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String rspid = rs.getString("pid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");
                String rsgrade = rs.getString("grade");
                if (rsgrade.equals("0.00"))
                    rsgrade = "F";
                if (rsgrade.equals("1.00"))
                    rsgrade = "D";
                if (rsgrade.equals("2.00"))
                    rsgrade = "C";
                if (rsgrade.equals("3.00"))
                    rsgrade = "B";
                if (rsgrade.equals("4.00"))
                    rsgrade = "A";
                Table newTable2 = new Table();
                newTable2.initSearchStudentCourseTable(rspid, rscourseLabel, rscourseName, rsquarter, rsschoolYear,
                        rsgrade);
                table2.getItems().add(newTable2);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

    }

    public String queryDataToSearchProfessorTable(
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

    public void createTableSearchStudentInClass(TableView<Table> table,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4) {
        column1.setCellValueFactory(new PropertyValueFactory<>("sid"));
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column4.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public String searchStudentsInClass(
            String cid,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String query = "SELECT distinct A.sid, A.firstName, A.lastName, B.grade " +
                    "FROM student A, grade B " +
                    "WHERE B.cid=? and B.sid = A.sid;";

            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, cid);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rssid = rs.getString("sid");
                String rsfirstName = rs.getString("firstName");
                String rslastName = rs.getString("lastName");
                String rsgrade = rs.getString("grade");

                Table newTable = new Table();
                newTable.initSearchStudentInClass(rssid, rsfirstName, rslastName, rsgrade);
                table.getItems().add(newTable);
            }

            connect.close();
            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }
    }

    public void createTableSearchCourseInQuarter(
            TableView<Table> table1,
            TableColumn<Table, String> column1,
            TableColumn<Table, String> column2,
            TableColumn<Table, String> column3,
            TableColumn<Table, String> column4,
            TableColumn<Table, String> column5,
            TableColumn<Table, String> column6) {
        column1.setCellValueFactory(new PropertyValueFactory<>("cid"));
        column2.setCellValueFactory(new PropertyValueFactory<>("courseLabel"));
        column3.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        column4.setCellValueFactory(new PropertyValueFactory<>("pid"));
        column5.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        column6.setCellValueFactory(new PropertyValueFactory<>("schoolYear"));
        table1.getColumns().add(column1);
        table1.getColumns().add(column2);
        table1.getColumns().add(column3);
        table1.getColumns().add(column4);
        table1.getColumns().add(column5);
        table1.getColumns().add(column6);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public String searchCourseByQuarterAndSchoolYear(
            String schoolYear,
            String quarter,
            TableView<Table> table) {
        try {
            table.getItems().clear();
            String query = "SELECT * FROM course WHERE schoolYear = ? AND quarter = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, schoolYear);
            preStatement.setString(2, quarter);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String rscid = rs.getString("cid");
                String rscourseLabel = rs.getString("courseLabel");
                String rscourseName = rs.getString("courseName");
                String rspid = rs.getString("pid");
                String rsquarter = rs.getString("quarter");
                String rsschoolYear = rs.getString("schoolYear");

                Table newTable = new Table();
                newTable.initSearchCoursesByProfessorId(rscid, rscourseLabel, rscourseName, rspid, rsquarter,
                        rsschoolYear);
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