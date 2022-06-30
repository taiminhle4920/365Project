package com.finalproject.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private static final String strNone = "!@#$%!@#$%";
    private static final Object objNone = "!@#$%!@#$%";

    // New Student /////////////////////////////////////////////////////////////
    @FXML
    private Label warningNS;
    @FXML
    private TextField firstNameNS;
    @FXML
    private TextField lastNameNS;
    @FXML
    private TextField emailNS;
    @FXML
    private DatePicker dobNS;
    @FXML
    private ChoiceBox<String> majorNS;

    @FXML
    private void onSubmitNS(ActionEvent actionEvent) {
        if (this.validate(this.firstNameNS.getText(), this.lastNameNS.getText(), this.emailNS.getText(), strNone,
                strNone, strNone, strNone, this.dobNS.getValue(), this.majorNS.getValue(), objNone, this.warningNS)) {
            String message = "Student " + this.firstNameNS.getText() + " " + this.lastNameNS.getText() + " is added.";
            this.warningNS.setTextFill(Color.color(0, 1, 0));
            this.warningNS.setText(message);

            // connect to sql here
        }

    }

    // New Professor ///////////////////////////////////////////////////////////
    @FXML
    private Label warningNP;
    @FXML
    private TextField firstNameNP;
    @FXML
    private TextField lastNameNP;
    @FXML
    private TextField emailNP;
    @FXML
    private DatePicker dobNP;

    @FXML
    private void onSubmitNP(ActionEvent actionEvent) {
        if (this.validate(this.firstNameNP.getText(), this.lastNameNP.getText(),
                this.emailNP.getText(), strNone,
                strNone, strNone, strNone, this.dobNP.getValue(), objNone, objNone, this.warningNP)) {
            String message = "Professor " + this.firstNameNP.getText() + " " +
                    this.lastNameNP.getText() + " is added.";
            this.warningNP.setTextFill(Color.color(0, 1, 0));
            this.warningNP.setText(message);

            // connect to sql here
        }
    }

    // New Course /////////////////////////////////////////////////////////////
    @FXML
    private TextField courseLabelNC;
    @FXML
    private TextField courseNameNC;
    @FXML
    private TextField instructorIdNC;
    @FXML
    private ChoiceBox<String> quarterNC;
    @FXML
    private ChoiceBox<String> schoolyearNC;
    @FXML
    private Label warningNC;

    @FXML
    private void onSubmitNC(ActionEvent actionEvent) {
        if (this.validate(strNone, strNone, strNone, this.courseLabelNC.getText(), this.courseNameNC.getText(),
                this.instructorIdNC.getText(), strNone, objNone, this.quarterNC.getValue(),
                this.schoolyearNC.getValue(),
                this.warningNC)) {
            String message = "Course " + this.courseLabelNC.getText() + " is added.";
            this.warningNC.setTextFill(Color.color(0, 1, 0));
            this.warningNC.setText(message);

            // connect to sql here
        }

    }

    // New Registration ////////////////////////////////////////////////////////
    @FXML
    private Label warningR;
    @FXML
    private TextField courseIDR;
    @FXML
    private TextField studentIDR;
    @FXML
    private ChoiceBox<String> gradeR;

    @FXML
    private void onSubmitR(ActionEvent actionEvent) {
        if (this.validate(strNone, strNone, strNone, strNone, strNone, this.courseIDR.getText(),
                this.studentIDR.getText(), objNone, this.gradeR.getValue(), objNone, this.warningR)) {
            String message = "Student " + this.studentIDR.getText() + " is registered.";
            this.warningR.setTextFill(Color.color(0, 1, 0));
            this.warningR.setText(message);

            // connect to sql here
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.majorNS.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer");

        this.quarterNC.getItems().addAll("Fall", "Winter", "Spring", "Summer");

        for (int i = 2025; i > 2000; i--) {
            this.schoolyearNC.getItems().add(String.valueOf(i));
        }

        this.gradeR.getItems().addAll("Letter Grade", "Pass/No Pass");
    }

    // clear event buttons
    @FXML
    public void onClearNS(ActionEvent actionEvent) {
        this.clear();
    }

    @FXML
    private void onClearNP(ActionEvent actionEvent) {
        this.clear();
    }

    @FXML
    private void onClearNC(ActionEvent actionEvent) {
        this.clear();
    }

    @FXML
    private void onClearR(ActionEvent actionEvent) {
        this.clear();
    }

    private void clear() {
        this.warningNS.setText("");
        this.firstNameNS.setText("");
        this.lastNameNS.setText("");
        this.emailNS.setText("");
        this.dobNS.setValue(null);
        this.majorNS.setValue(null);

        this.warningNP.setText("");
        this.firstNameNP.setText("");
        this.lastNameNP.setText("");
        this.emailNP.setText("");
        this.dobNP.setValue(null);

        this.warningNC.setText("");
        this.courseLabelNC.setText("");
        this.courseNameNC.setText("");
        this.instructorIdNC.setText("");
        this.quarterNC.setValue(null);
        this.schoolyearNC.setValue(null);

        this.warningR.setText("");
        this.courseIDR.setText("");
        this.studentIDR.setText("");
        this.gradeR.setValue(null);
    }

    private boolean validate(
            String firstName,
            String lastName,
            String email,
            String courseLabel,
            String courseName,
            String id,
            String id2,
            Object datePicker,
            Object choiceBox,
            Object choiceBox2,
            Label warning) {
        warning.setTextFill(Color.color(1, 0, 0));
        if (firstName != strNone && !firstName.matches("^[a-zA-Z]{3,20}$")) {
            warning.setText("First Name must contain only alphabets.\nFirst Name must be 3-20 characters long.");
            return false;
        } else if (lastName != strNone && !lastName.matches("^[a-zA-Z]{3,20}$")) {
            warning.setText("Last Name must contain only alphabets.\nLast Name must be 3-20 characters long.");
            return false;
        } else if (email != strNone && !email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            warning.setText("Email is not valid");
            return false;
        } else if (courseLabel != strNone && !courseLabel.matches("^[a-zA-Z0-9 ]{6,20}$")) {
            warning.setText(
                    "Course Title must be 6-20 characters long.\nCourse Title contains only numbers and alphabets.");
            return false;
        } else if (courseName != strNone && !courseName.matches("^[a-zA-Z0-9!@#$%&*()'+,-./:;<=>?^_`{|} ]{6,}$")) {
            warning.setText("Course Name must be 6 or more characters long.");
            return false;
        } else if (id != strNone && !id.matches("^[0-9]{1,100}$")) {
            warning.setText("ID must contain only numbers.");
            return false;
        } else if (id2 != strNone && !id2.matches("^[0-9]{1,100}$")) {
            warning.setText("Both IDs must contain only numbers.");
            return false;
        } else if (datePicker != objNone && datePicker == null) {
            warning.setText("Day of birth can't be empty.");
            return false;
        } else if (choiceBox != objNone && choiceBox == null) {
            warning.setText("Choice box can't not be empty.");
            return false;
        } else if (choiceBox2 != objNone && choiceBox2 == null) {
            warning.setText("Choice box can't not be empty");
            return false;
        } else
            return true;
    }

}