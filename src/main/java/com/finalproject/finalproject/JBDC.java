package com.finalproject.finalproject;

import java.sql.*;

public class JBDC {

    Connection connect;
    PreparedStatement insert;
    private static String CONNECTION = "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/phutai?user=phutai&password=phutai";
    private static String DRIVER = "com.mysql.jdbc.Driver";

    public JBDC() {
    }

    public boolean makeConnection() {
        try {
            Class.forName(DRIVER);
            this.connect = DriverManager.getConnection(CONNECTION);
            return true;
        } catch (Exception e) {
            this.printSQLException((SQLException) e);
            return false;
        }
    }

    public void printSQLException(SQLException ex) {
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

    public boolean insertStudent(String firstName, String lastName, String email, String dob, String major) {
        try {
            String sql = "INSERT INTO " +
                            "student (firstName, lastName, email, dob, major) " +
                            "VALUES " + 
                            "( ?, ?, ?, ?, ? );";

            this.makeConnection();
            this.insert = this.connect.prepareStatement(sql);
            this.insert.setString(1,firstName);
            this.insert.setString(2,lastName);
            this.insert.setString(3,email);
            this.insert.setString(4,dob);
            this.insert.setString(5,major);
            this.insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            this.printSQLException(e);
            return false;
        }
    }

}
