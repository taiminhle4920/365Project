package com.finalproject.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.finalproject.finalproject.Tables.Student;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class DashboardController implements Initializable {

    JBDCInsert jbdc = new JBDCInsert();
    // JBDCRemove jbdcRemove = new JBDCRemove();

    // Add Student /////////////////////////////////////////////////////////////
    @FXML
    private Label errorAddStudent;
    @FXML
    private TextField firstNameAS;
    @FXML
    private TextField lastNameAS;
    @FXML
    private TextField emailAS;
    @FXML
    private DatePicker dobAS;
    @FXML
    private ChoiceBox<String> majorAS;

    @FXML
    private void onSubmitAddStudent(ActionEvent actionEvent) {
        this.errorAddStudent.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.firstNameAS.getText(), this.errorAddStudent,
                "First name must be 3 or more characters long.")
                &&
                nameValidator(this.lastNameAS.getText(), this.errorAddStudent,
                        "Last name must be 3 or more characters long.")
                &&
                emailValidator(this.emailAS.getText(), this.errorAddStudent, "Email is invalid.") &&
                datePickerValidator(this.dobAS.getValue(), this.errorAddStudent, "Day of birth field is required.") &&
                choiceBoxPicker(this.majorAS.getValue(), this.errorAddStudent, "Major field is required.")) {

            // connect to sql here.
            String message = jbdc.insertStudent(this.firstNameAS.getText(),
                    this.lastNameAS.getText(),
                    this.emailAS.getText(),
                    this.dobAS.getValue().toString(),
                    this.majorAS.getValue());
            if (message == null) {
                this.errorAddStudent.setTextFill(Color.color(0, 1, 0));
                this.errorAddStudent.setText("A new student is added.");
            } else {
                this.errorAddStudent.setTextFill(Color.color(1, 0, 0));
                this.errorAddStudent.setText(message);
            }

            this.clear();
        }

    }

    // Add Professor ///////////////////////////////////////////////////////////
    @FXML
    private Label errorAddProfessor;
    @FXML
    private TextField firstNameAP;
    @FXML
    private TextField lastNameAP;
    @FXML
    private TextField emailAP;
    @FXML
    private DatePicker dobAP;

    @FXML
    private void onSubmitAddProfessor(ActionEvent actionEvent) {
        this.errorAddProfessor.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.firstNameAP.getText(), this.errorAddProfessor,
                "First name must be 3 or more characters long.")
                &&
                nameValidator(this.lastNameAP.getText(), this.errorAddProfessor,
                        "Last name must be 3 or more characters long.")
                &&
                emailValidator(this.emailAP.getText(), this.errorAddProfessor, "Email is invalid.") &&
                datePickerValidator(this.dobAP.getValue(), this.errorAddProfessor, "Day of birth field is required.")) {

            // connect to sql here.
            String message = jbdc.insertProfessor(this.firstNameAP.getText(),
                    this.lastNameAP.getText(),
                    this.emailAP.getText(),
                    this.dobAP.getValue().toString());
            if (message == null) {
                this.errorAddProfessor.setTextFill(Color.color(0, 1, 0));
                this.errorAddProfessor.setText("A new professor is added.");
            } else {
                this.errorAddProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorAddProfessor.setText(message);
            }

            this.clear();
        }

    }

    // Add Course /////////////////////////////////////////////////////////////
    @FXML
    private Label errorAddCourse;
    @FXML
    private TextField courseLabelAC;
    @FXML
    private TextField courseNameAC;
    @FXML
    private TextField instructorIdAC;
    @FXML
    private ChoiceBox<String> quarterAC;
    @FXML
    private ChoiceBox<String> schoolyearAC;

    @FXML
    private void onSubmitAddCourse(ActionEvent actionEvent) {
        this.errorAddCourse.setTextFill(Color.color(1, 0, 0));
        if (nameValidator(this.courseLabelAC.getText(), this.errorAddCourse,
                "Course title must be 3 or more characters long.") &&
                nameValidator(this.courseNameAC.getText(), this.errorAddCourse,
                        "Course name must be 3 or more characters long.")
                &&
                idValidator(this.instructorIdAC.getText(), this.errorAddCourse,
                        "Instructor ID must contain numbers only.")
                &&
                choiceBoxPicker(this.quarterAC.getValue(), this.errorAddCourse, "Quarter field is required.") &&
                choiceBoxPicker(this.schoolyearAC.getValue(), this.errorAddCourse, "School year field is required.")) {

            // connect to sql here.
            String message = jbdc.insertCourse(this.courseLabelAC.getText(),
                    this.courseNameAC.getText(),
                    this.instructorIdAC.getText(),
                    this.quarterAC.getValue().toString(),
                    this.schoolyearAC.getValue().toString());
            if (message == null) {
                this.errorAddCourse.setTextFill(Color.color(0, 1, 0));
                this.errorAddCourse.setText("A new course is added.");
            } else {
                this.errorAddCourse.setTextFill(Color.color(1, 0, 0));
                this.errorAddCourse.setText(message);
            }

            this.clear();
        }

    }

    // Add Grade ////////////////////////////////////////////////////////
    @FXML
    private Label errorAddGrade;
    @FXML
    private TextField courseIDAG;
    @FXML
    private TextField studentIDAG;
    @FXML
    private ChoiceBox<String> gradeAG;

    @FXML
    private void onSubmitAddGrade(ActionEvent actionEvent) {
        this.errorAddGrade.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIDAG.getText(), this.errorAddGrade, "Course ID must contain numbers only.") &&
                idValidator(this.studentIDAG.getText(), this.errorAddGrade, "Student ID must contain numbers only.") &&
                choiceBoxPicker(this.gradeAG.getValue(), this.errorAddGrade, "School year field is required.")) {

            // connect to sql here.
            String message = jbdc.insertGrade(this.courseIDAG.getText(), this.studentIDAG.getText(),
                    this.gradeAG.getValue());
            if (message == null) {
                this.errorAddGrade.setTextFill(Color.color(0, 1, 0));
                this.errorAddGrade.setText("A new grade is added.");
            } else {
                this.errorAddGrade.setTextFill(Color.color(1, 0, 0));
                this.errorAddGrade.setText(message);
            }

            this.clear();
        }
    }

    // Search Student
    @FXML
    private Label errorSearchStudent;
    @FXML
    private TextField studentIdSS;
    @FXML
    private TableView<?> tableStudent;
    @FXML
    private TableColumn<?, ?> colIDSearchStudent;
    @FXML
    private TableColumn<?, ?> colFNSearchStudent;
    @FXML
    private TableColumn<?, ?> colLNSearchStudent;
    @FXML
    private TableColumn<?, ?> colEmailSearchStudent;
    @FXML
    private TableColumn<?, ?> colDOBSearchStudent;
    @FXML
    private TableView<?> tableCourseStudent;
    @FXML
    private TableColumn<?, ?> colCourseIDSearchStudent;
    @FXML
    private TableColumn<?, ?> colCourseLabelSearchStudent;
    @FXML
    private TableColumn<?, ?> colCourseNameSearchStudent;
    @FXML
    private TableColumn<?, ?> colInstructorIDSearchStudent;
    @FXML
    private TableColumn<?, ?> colQuarterSearchStudent;
    @FXML
    private TableColumn<?, ?> colYearSearchStudent;

    @FXML
    private void onSubmitSearchStudent(ActionEvent actionEvent) {
        this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.studentIdSS.getText(), this.errorSearchStudent, "Student ID must contain numbers only.")) {
            this.clear();
            this.errorSearchStudent.setTextFill(Color.color(0, 1, 0));
            this.errorSearchStudent.setText("Searching for student");

            // connect to sql here.

        }

    }

    // Search Professor
    @FXML
    private Label errorSearchProfessor;
    @FXML
    private TextField professorIdSP;
    @FXML
    private TableView<?> tableInstructor;
    @FXML
    private TableColumn<?, ?> colIDSearchInstructor;
    @FXML
    private TableColumn<?, ?> colFNSearchInstructor;
    @FXML
    private TableColumn<?, ?> colLNSearchInstructor;
    @FXML
    private TableColumn<?, ?> colEmailSearchInstructor;
    @FXML
    private TableColumn<?, ?> colDOBSearchInstructor;
    @FXML
    private TableView<?> tableCourseInstructor;
    @FXML
    private TableColumn<?, ?> colCourseIDSearchInstructor;
    @FXML
    private TableColumn<?, ?> colCourseLabelSearchInstructor;
    @FXML
    private TableColumn<?, ?> colCourseNameSearchInstructor;
    @FXML
    private TableColumn<?, ?> colInstructorIDSearchInstructor;
    @FXML
    private TableColumn<?, ?> colQuarterSearchInstructor;
    @FXML
    private TableColumn<?, ?> colYearSearchInstructor;

    @FXML
    private void onSubmitSearchProfessor(ActionEvent actionEvent) {
        this.errorSearchProfessor.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.professorIdSP.getText(), this.errorSearchProfessor,
                "Professor ID must contain numbers only.")) {
            this.clear();
            this.errorSearchProfessor.setTextFill(Color.color(0, 1, 0));
            this.errorSearchProfessor.setText("Searching for professor");

            // connect to sql here.

        }

    }

    // Search Student in class
    @FXML
    private TextField courseIDSSC;
    @FXML
    private Label errorSearchStudentsInClass;
    @FXML
    private TableView<?> tableCourse;
    @FXML
    private TableColumn<?, ?> colStudentIDSearchCourse;
    @FXML
    private TableColumn<?, ?> colFNSearchCourse;
    @FXML
    private TableColumn<?, ?> colLNSearchCourse;
    @FXML
    private TableColumn<?, ?> colGradeSearchCourse;

    @FXML
    private void onSubmitSearchStudentsInClass(ActionEvent actionEvent) {
        this.errorSearchStudentsInClass.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIDSSC.getText(), this.errorSearchStudentsInClass,
                "Course ID must contain numbers only.")) {
            this.clear();
            this.errorSearchStudentsInClass.setTextFill(Color.color(0, 1, 0));
            this.errorSearchStudentsInClass.setText("Searching for students.");

            // connect to sql here.

        }

    }

    // Search Course List
    @FXML
    private Label errorSearchCourseList;
    @FXML
    private ChoiceBox<String> schoolYearSCL;
    @FXML
    private ChoiceBox<String> quarterSCL;
    @FXML
    private TableView<?> tableCourseFindCourse;
    @FXML
    private TableColumn<?, ?> colCourseIDFindCourse;
    @FXML
    private TableColumn<?, ?> colCourseLabelFindCourse;
    @FXML
    private TableColumn<?, ?> colCourseNameFindCourse;
    @FXML
    private TableColumn<?, ?> colInstructorIDFindCourse;
    @FXML
    private TableColumn<?, ?> colQuarterFindCourse;
    @FXML
    private TableColumn<?, ?> colYearFindCourse;

    @FXML
    private void onSubmitSearchCourseList(ActionEvent actionEvent) {
        this.errorSearchCourseList.setTextFill(Color.color(1, 0, 0));
        if (choiceBoxPicker(this.quarterSCL.getValue(), this.errorSearchCourseList, "Quarter field is required.") &&
                choiceBoxPicker(this.schoolYearSCL.getValue(), this.errorSearchCourseList,
                        "School year field is required.")) {
            this.clear();
            this.errorSearchCourseList.setTextFill(Color.color(0, 1, 0));
            this.errorSearchCourseList.setText("Searching for courses.");

            // connect to sql here.

        }

    }

    // Remove student
    @FXML
    private Label errorRemoveStudent;
    @FXML
    private TextField studentIdRS;

    @FXML
    private TableView<Student> removeStudentTable = new TableView<Student>();

    @FXML
    private TableColumn<Student, String> column1RemoveStudent = new TableColumn<>("ID");

    @FXML
    private TableColumn<Student, String> column2RemoveStudent = new TableColumn<>("First Name");

    @FXML
    private TableColumn<Student, String> column3RemoveStudent = new TableColumn<>("Last Name");

    @FXML
    private TableColumn<Student, String> column4RemoveStudent = new TableColumn<>("Email");

    @FXML
    private TableColumn<Student, String> column5RemoveStudent = new TableColumn<>("Day of Birth");

    @FXML
    private TableColumn<Student, String> column6RemoveStudent = new TableColumn<>("Major");

    @FXML
    private void onSubmitRemoveStudent(ActionEvent actionEvent) {
        this.errorRemoveStudent.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.studentIdRS.getText(), this.errorRemoveStudent, "Student ID must contain numbers only.")) {
            this.clear();
            this.errorRemoveStudent.setTextFill(Color.color(0, 1, 0));
            this.errorRemoveStudent.setText("A student is removed.");

            // connect to sql here.

        }

    }

    // Remove professor
    @FXML
    private Label errorRemoveProfessor;
    @FXML
    private TextField professorIdRP;

    @FXML
    private void onSubmitRemoveProfessor(ActionEvent actionEvent) {
        this.errorRemoveProfessor.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.professorIdRP.getText(), this.errorRemoveProfessor,
                "Professor ID must contain numbers only.")) {
            this.clear();
            this.errorRemoveProfessor.setTextFill(Color.color(0, 1, 0));
            this.errorRemoveProfessor.setText("A professor is removed.");

            // connect to sql here.

        }

    }

    // Remove course
    @FXML
    private Label errorRemoveCourse;
    @FXML
    private TextField courseIdRC;

    @FXML
    public void onSubmitRemoveCourse(ActionEvent actionEvent) {
        this.errorRemoveCourse.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIdRC.getText(), this.errorRemoveCourse, "Course ID must contain numbers only.")) {
            this.clear();
            this.errorRemoveCourse.setTextFill(Color.color(0, 1, 0));
            this.errorRemoveCourse.setText("A course is removed.");

            // connect to sql here.

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.majorAS.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer");

        this.quarterAC.getItems().addAll("Fall", "Winter", "Spring", "Summer");
        this.quarterSCL.getItems().addAll("Fall", "Winter", "Spring", "Summer");

        for (int i = 2025; i > 2000; i--) {
            this.schoolyearAC.getItems().add(String.valueOf(i));
            this.schoolYearSCL.getItems().add(String.valueOf(i));
        }

        this.gradeAG.getItems().addAll("A", "B", "C", "D", "E", "F");

        // JBDCRemove.createColumnRemoveStudent(this.removeStudentTable, this.column1RemoveStudent,
        //         this.column2RemoveStudent, this.column3RemoveStudent, this.column4RemoveStudent,
        //         this.column5RemoveStudent, this.column6RemoveStudent);

    }

    // clear event buttons
    @FXML
    public void onClearAddStudent(ActionEvent actionEvent) {
        this.errorAddStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddProfessor(ActionEvent actionEvent) {
        this.errorAddProfessor.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddCourse(ActionEvent actionEvent) {
        this.errorAddCourse.setText("");
        this.clear();
    }

    @FXML
    private void onClearAddGrade(ActionEvent actionEvent) {
        this.errorAddGrade.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchStudent(ActionEvent actionEvent) {
        this.errorSearchStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchProfessor(ActionEvent actionEvent) {
        this.errorSearchProfessor.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchStudentsInClass(ActionEvent actionEvent) {
        this.errorSearchStudentsInClass.setText("");
        this.clear();
    }

    @FXML
    private void onClearSearchCourseList(ActionEvent actionEvent) {
        this.errorSearchCourseList.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveStudent(ActionEvent actionEvent) {
        this.errorRemoveStudent.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveCourse(ActionEvent actionEvent) {
        this.errorRemoveCourse.setText("");
        this.clear();
    }

    @FXML
    private void onClearRemoveProfessor(ActionEvent actionEvent) {
        this.errorRemoveProfessor.setText("");
        this.clear();
    }

    private void clear() {
        this.firstNameAS.setText("");
        this.lastNameAS.setText("");
        this.emailAS.setText("");
        this.dobAS.setValue(null);
        this.majorAS.setValue(null);

        this.firstNameAP.setText("");
        this.lastNameAP.setText("");
        this.emailAP.setText("");
        this.dobAP.setValue(null);

        this.courseLabelAC.setText("");
        this.courseNameAC.setText("");
        this.instructorIdAC.setText("");
        this.quarterAC.setValue(null);
        this.schoolyearAC.setValue(null);

        this.courseIDAG.setText("");
        this.studentIDAG.setText("");
        this.gradeAG.setValue(null);

        this.studentIdSS.setText("");

        this.professorIdSP.setText("");

        this.courseIDSSC.setText("");

        this.quarterSCL.setValue(null);
        this.schoolYearSCL.setValue(null);

        this.studentIdRS.setText("");

        this.courseIdRC.setText("");

        this.professorIdRP.setText("");
    }

    public static boolean choiceBoxPicker(String choiceBox, Label warning, String message) {
        if (choiceBox == null) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean datePickerValidator(LocalDate date, Label warning, String message) {
        if (date == null) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean emailValidator(String email, Label warning, String message) {
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean idValidator(String id, Label warning, String message) {
        if (!id.matches("^[0-9]{1,}$")) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

    public static boolean nameValidator(String name, Label warning, String message) {
        if (!(name.length() >= 3)) {
            warning.setText(message);
            return false;
        } else
            return true;
    }

}