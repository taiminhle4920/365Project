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

    private static final String NONE = "!@#$%^&*()";

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
    private ChoiceBox majorNS;

    @FXML
    private void onSubmitNS(ActionEvent actionEvent) {
        if (this.validate(this.firstNameNS.getText(), this.lastNameNS.getText(), this.emailNS.getText(),
                this.dobNS.getValue(), this.majorNS.getValue(), this.warningNS)) {
            String message = "Student " + this.firstNameNS.getText() + " " + this.lastNameNS.getText() + " is added.";
            this.warningNS.setTextFill(Color.color(0, 1, 0));
            this.warningNS.setText(message);

            // connect to sql here
        }

    }

    // clear event button
    @FXML
    public void onClearNS(ActionEvent actionEvent) {
        this.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.majorNS.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer");

    }

    // helper functions
    private void clear() {
        this.majorNS.setValue(null);
        this.dobNS.setValue(null);

        this.firstNameNS.setText("");
        this.lastNameNS.setText("");
        this.emailNS.setText("");

        this.warningNS.setText("");
    }

    private boolean validate(
            String firstName,
            String lastName,
            String email,
            Object dob,
            Object major,
            Label warning) {
        warning.setTextFill(Color.color(1, 0, 0));
        if (firstName != NONE && !firstName.matches("^[a-zA-Z]{3,20}$")) {
            warning.setText("First Name must contains only alphabets and be 3-20 characters long.");
            return false;
        } else if (lastName != NONE && !lastName.matches("^[a-zA-Z]{3,20}$")) {
            warning.setText("Last Name must contains only alphabets and be 3-20 characters long.");
            return false;
        } else if (email != NONE && !email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            warning.setText("Email is not valid");
            return false;
        } else if (dob != NONE && dob == null) {
            warning.setText("Must pick a birthday.");
            return false;
        } else if (major != NONE && major == null) {
            warning.setText("Must pick a major.");
            return false;
        } else
            return true;
    }

}