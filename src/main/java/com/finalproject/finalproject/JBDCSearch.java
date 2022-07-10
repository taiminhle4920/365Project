package com.finalproject.finalproject;

import com.finalproject.finalproject.Tables.Student;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JBDCSearch {

    JDBCConnection connection = new JDBCConnection();

    public JBDCSearch() {
    }

    public String searchStudent(
            TableView<Student> table1,
            TableColumn<Student, String> column1,
            TableColumn<Student, String> column2,
            TableColumn<Student, String> column3,
            TableColumn<Student, String> column4,
            TableColumn<Student, String> column5,
            TableColumn<Student, String> column6,
            TableView<Student> table2,
            TableColumn<Student, String> column7,
            TableColumn<Student, String> column8,
            TableColumn<Student, String> column9,
            TableColumn<Student, String> column10,
            TableColumn<Student, String> column11,
            TableColumn<Student, String> column12) {

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

        column7.setCellValueFactory(new PropertyValueFactory<>("cid"));
        column8.setCellValueFactory(new PropertyValueFactory<>("courseLabel"));
        column9.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        column10.setCellValueFactory(new PropertyValueFactory<>("pid"));
        column11.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        column12.setCellValueFactory(new PropertyValueFactory<>("schoolyear"));
        table2.getColumns().add(column7);
        table2.getColumns().add(column8);
        table2.getColumns().add(column9);
        table2.getColumns().add(column10);
        table2.getColumns().add(column11);
        table2.getColumns().add(column12);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return null;
    }

}