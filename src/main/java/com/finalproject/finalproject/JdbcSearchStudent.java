package com.finalproject.finalproject;

import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JdbcSearchStudent {

    JdbcConnection jdbcConnection = new JdbcConnection();

    public JdbcSearchStudent() {
    }

    public void createSearchStudentTable(TableView<Student> table1,
            TableColumn<Student, String> column1,
            TableColumn<Student, String> column2,
            TableColumn<Student, String> column3,
            TableColumn<Student, String> column4,
            TableColumn<Student, String> column5,
            TableColumn<Student, String> column6,
            TableView<StudentCourse> table2,
            TableColumn<StudentCourse, String> column7,
            TableColumn<StudentCourse, String> column8,
            TableColumn<StudentCourse, String> column9,
            TableColumn<StudentCourse, String> column10,
            TableColumn<StudentCourse, String> column11,
            TableColumn<StudentCourse, String> column12) {

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

        column7.setCellValueFactory(new PropertyValueFactory<>("pid"));
        column8.setCellValueFactory(new PropertyValueFactory<>("courseLabel"));
        column9.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        column10.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        column11.setCellValueFactory(new PropertyValueFactory<>("schoolYear"));
        column12.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table2.getColumns().add(column7);
        table2.getColumns().add(column8);
        table2.getColumns().add(column9);
        table2.getColumns().add(column10);
        table2.getColumns().add(column11);
        table2.getColumns().add(column12);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

    public String searchStudent(
            String sid,
            TableView<Student> table1,
            TableView<StudentCourse> table2) {
        try {
            table1.getItems().clear();
            table2.getItems().clear();

            String sql = "SELECT * FROM student WHERE sid = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(sid));
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()) {
                String tempsid = rs.getString("sid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String major = rs.getString("major");

                table1.getItems().add(new Student(tempsid, firstName, lastName, email, dob, major));
            }

            sql = "SELECT DISTINCT " +
                    "course.pid, course.courseLabel, course.courseName, course.quarter, course.schoolYear, grade.grade "
                    +
                    "FROM grade INNER JOIN course on grade.cid = course.cid " +
                    "WHERE grade.sid = ?;";

            this.jdbcConnection.makeConnection();
            connect = this.jdbcConnection.getConnection();
            preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(sql);
            preStatement.setInt(1, Integer.valueOf(sid));
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String pid = rs.getString("pid");
                String courseLabel = rs.getString("courseLabel");
                String courseName = rs.getString("courseName");
                String quarter = rs.getString("quarter");
                String schoolYear = rs.getString("schoolYear");
                String grade = rs.getString("grade");
                String tempGrade = null;
                if (grade.equals("0.00"))
                    tempGrade = "F";
                if (grade.equals("1.00"))
                    tempGrade = "D";
                if (grade.equals("2.00"))
                    tempGrade = "C";
                if (grade.equals("3.00"))
                    tempGrade = "B";
                if (grade.equals("4.00"))
                    tempGrade = "A";
                table2.getItems().add(new StudentCourse(pid, courseLabel, courseName, quarter, schoolYear, tempGrade));
            }

            return null;
        } catch (SQLException e) {
            JdbcConnection.printSQLException(e);
            return e.getMessage();
        }

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
    public void createTableSearchStudentInClass(TableView<Student> table,
                                                TableColumn<Student, String> column1,
                                                TableColumn<Student, String> column2,
                                                TableColumn<Student, String> column3,
                                                TableColumn<Student, String> column4){
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

    public String searchStudentInclass(String cid, TableView<Student>table){
        try{
            table.getItems().clear();
            String query = "SELECT distinct A.sid, A.firstName, A.lastName, B.grade FROM student A, grade B WHERE B.cid=? and B.sid = A.sid;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setInt(1, Integer.valueOf(cid));
            ResultSet rs = preStatement.executeQuery();
            while(rs.next()){
                String tempsid = rs.getString("sid");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String grade = rs.getString("grade");
                table.getItems().add(new Student(tempsid, firstName, lastName, grade));
            }
            return null;

        }catch (SQLException e){
            jdbcConnection.printSQLException(e);
            return e.getMessage();
        }
    }

    public void createTableSearchCourseInQuarter(TableView<CourseSearch> table1,
                                                 TableColumn<CourseSearch, String> column1,
                                                 TableColumn<CourseSearch, String> column2,
                                                 TableColumn<CourseSearch, String> column3,
                                                 TableColumn<CourseSearch, String> column4,
                                                 TableColumn<CourseSearch, String> column5,
                                                 TableColumn<CourseSearch, String> column6){

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
    public String searchCourseInQuarter(String schoolYear, String quarter, TableView<CourseSearch>table){
        try{
            table.getItems().clear();
            String query = "SELECT * FROM course WHERE schoolYear=? AND quarter = ?;";
            this.jdbcConnection.makeConnection();
            Connection connect = this.jdbcConnection.getConnection();
            PreparedStatement preStatement = this.jdbcConnection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setInt(1, Integer.valueOf(schoolYear));
            if(quarter.equals("Fall")){
                preStatement.setInt(2, 1);
            }else if(quarter.equals("Winter")){
                preStatement.setInt(2, 2);
            }else if(quarter.equals("Spring")){
                preStatement.setInt(2, 3);
            }else {
                preStatement.setInt(2, 4);
            }
            System.out.println(preStatement);
            ResultSet rs = preStatement.executeQuery();
            while(rs.next()){
                String tempcid = rs.getString("cid");
                String courseLabel = rs.getString("courseLabel");
                String courseName = rs.getString("courseName");
                String temppid = rs.getString("pid");
                String tempQuarter = rs.getString("quarter");
                String year = rs.getString("schoolYear");
                table.getItems().add(new CourseSearch(tempcid, courseLabel, courseName, temppid, tempQuarter, year));
            }
            return null;

        }catch (SQLException e){
            jdbcConnection.printSQLException(e);
            return e.getMessage();
        }
    }

}
