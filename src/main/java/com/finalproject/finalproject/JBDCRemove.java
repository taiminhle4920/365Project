// package com.finalproject.finalproject;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// import com.finalproject.finalproject.Tables.Student;

// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;

// public class JBDCRemove {

//     Connection connect;
//     PreparedStatement preparedStatement;
//     private static String CONNECTION = "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/phutai?user=phutai&password=phutai";
//     private static String DRIVER = "com.mysql.jdbc.Driver";

//     public JBDCRemove() {
//         this.makeConnection();
//     }

//     public boolean makeConnection() {
//         try {
//             Class.forName(DRIVER);
//             this.connect = DriverManager.getConnection(CONNECTION);
//             String sql = "SELECT * FROM student;";
//             this.preparedStatement = this.connect.prepareStatement(sql);
//             ResultSet rs = this.preparedStatement.executeQuery();
//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 String firstName = rs.getString("firstName");
//                 String lastName = rs.getString("lastName");
//                 System.out.println("id : " + id);
//                 System.out.println("firstName : " + firstName);
//                 System.out.println("lastName : " + lastName);
//             }
//             return true;
//         } catch (Exception e) {
//             JBDCInsert.printSQLException((SQLException) e);
//             return false;
//         }
//     }



//     public void createColumnRemoveStudent(
//             TableView<Student> table,
//             TableColumn<Student, String> column1,
//             TableColumn<Student, String> column2,
//             TableColumn<Student, String> column3,
//             TableColumn<Student, String> column4,
//             TableColumn<Student, String> column5,
//             TableColumn<Student, String> column6) {
//         column1.setCellValueFactory(new PropertyValueFactory<>("id"));
//         column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//         column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//         column4.setCellValueFactory(new PropertyValueFactory<>("email"));
//         column5.setCellValueFactory(new PropertyValueFactory<>("dob"));
//         column6.setCellValueFactory(new PropertyValueFactory<>("major"));
//         table.getColumns().add(column1);
//         table.getColumns().add(column2);
//         table.getColumns().add(column3);
//         table.getColumns().add(column4);
//         table.getColumns().add(column5);
//         table.getColumns().add(column6);
//         table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//     }

// }
