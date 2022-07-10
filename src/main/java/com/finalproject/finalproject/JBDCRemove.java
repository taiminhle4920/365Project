package com.finalproject.finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.finalproject.finalproject.JDBCConnection.printSQLException;

public class JBDCRemove {

    JDBCConnection connection = new JDBCConnection();

    public String removeByStudentID(String studentID) {
        try {
            this.connection.makeConnection();
            String query = "DELETE FROM student WHERE sid=?;";
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, studentID);
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String removeByProfessorID(String professorID) {
        try {
            this.connection.makeConnection();
            String query = "DELETE FROM professor where pid=?;";
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, professorID);
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

    public String removeByCourseID(String courseID) {
        try {
            String query = "DELETE FROM course where cid=?;";
            this.connection.makeConnection();
            Connection connect = this.connection.getConnection();
            PreparedStatement preStatement = this.connection.getPreparedStatement();
            preStatement = connect.prepareStatement(query);
            preStatement.setString(1, courseID);
            preStatement.executeUpdate();
            connect.close();
            return null;
        } catch (SQLException e) {
            printSQLException(e);
            return e.getMessage();
        }
    }

}
