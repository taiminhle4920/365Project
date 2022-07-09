package com.finalproject.finalproject;

import java.sql.*;

public class JBDCInsert {

    Connection connect;
    PreparedStatement insert;
    private static String CONNECTION = "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/phutai?user=phutai&password=phutai";
    private static String DRIVER = "com.mysql.jdbc.Driver";

    public JBDCInsert() {
    }

    public boolean makeConnection() {
        try {
            Class.forName(DRIVER);
            this.connect = DriverManager.getConnection(CONNECTION);
            return true;
        } catch (Exception e) {
            printSQLException((SQLException) e);
            return false;
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (ex instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public String insertStudent(String firstName, String lastName, String email, String dob, String major) {
        try {
            String sql = "INSERT INTO " +
                    "student (firstName, lastName, email, dob, major) " +
                    "VALUES " +
                    "( ?, ?, ?, ?, ? );";

            this.makeConnection();
            this.insert = this.connect.prepareStatement(sql);
            this.insert.setString(1, firstName);
            this.insert.setString(2, lastName);
            this.insert.setString(3, email);
            this.insert.setString(4, dob);
            this.insert.setString(5, major);
            this.insert.executeUpdate();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String insertProfessor(String firstName, String lastName, String email, String dob) {
        try {
            String sql = "INSERT INTO " +
                    "professor (firstName, lastName, email, dob) " +
                    "VALUES " +
                    "( ?, ?, ?, ?);";

            this.makeConnection();
            this.insert = this.connect.prepareStatement(sql);
            this.insert.setString(1, firstName);
            this.insert.setString(2, lastName);
            this.insert.setString(3, email);
            this.insert.setString(4, dob);
            this.insert.executeUpdate();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String insertCourse(String courseTitle, String courseName, String professorId, String quarter,
            String schoolYear) {
        try {
            String sql = "INSERT INTO " +
                    "course (courseLabel, courseName, professorId, quarter, schoolYear) " +
                    "VALUES " +
                    "( ?, ?, ?, ?, ?);";

            this.makeConnection();
            this.insert = this.connect.prepareStatement(sql);
            this.insert.setString(1, courseTitle);
            this.insert.setString(2, courseName);
            this.insert.setInt(3, Integer.valueOf(professorId));
            this.insert.setString(4, quarter);
            this.insert.setInt(5, Integer.valueOf(schoolYear));
            this.insert.executeUpdate();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String insertGrade(String courseId, String studentId, String grade) {
        try {
            String sql = "INSERT INTO " +
                    "grade (courseId, studentId, grade) " +
                    "VALUES " +
                    "( ?, ?, ?);";

            this.makeConnection();
            this.insert = this.connect.prepareStatement(sql);
            this.insert.setInt(1, Integer.valueOf(courseId));
            this.insert.setInt(2, Integer.valueOf(studentId));
            this.insert.setString(3, grade);
            this.insert.executeUpdate();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

}
