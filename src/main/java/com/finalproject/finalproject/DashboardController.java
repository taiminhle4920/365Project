package com.finalproject.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DashboardController implements Initializable {

    private JdbcInsert jbdcInsert = new JdbcInsert();
    private JdbcRemove jbdcRemove = new JdbcRemove();
    private JdbcSearchStudent jdbcSearchStudent = new JdbcSearchStudent();
    private JdbcSearchStudent jdbcSearchProfessor = new JdbcSearchStudent();
    private JdbcFindStudentByGpa jdbcFindStudentByGpa = new JdbcFindStudentByGpa();
    private JdbcFindProfessorByGpa jdbcFindProfessorByGpa = new JdbcFindProfessorByGpa();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.majorAS.getItems().addAll(
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.quarterAC.getItems().addAll("Fall", "Winter", "Spring", "Summer");
        this.quarterSCL.getItems().addAll("Fall", "Winter", "Spring", "Summer");

        for (int i = 2025; i > 2000; i--) {
            this.schoolyearAC.getItems().add(String.valueOf(i));
            this.schoolYearSCL.getItems().add(String.valueOf(i));
        }

        this.gradeAG.getItems().addAll("A", "B", "C", "D", "E", "F");

        this.findStudentGpaChoiceBox.setValue("All");
        this.findStudentGpaChoiceBox.getItems().addAll(
                "All",
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.findProfessorGpaChoiceBox.setValue("All");
        this.findProfessorGpaChoiceBox.getItems().addAll(
                "All",
                "Computer Science",
                "Software Engineer",
                "Hardware Engineer",
                "Information Technology",
                "Information Systems Security",
                "Computer Engineering");

        this.jdbcSearchStudent.createSearchStudentTable(
                this.searchStudentTable,
                this.sstColumn1,
                this.sstColumn2,
                this.sstColumn3,
                this.sstColumn4,
                this.sstColumn5,
                this.sstColumn6,
                this.searchStudentCourseTable,
                this.sstcColumn1,
                this.sstcColumn2,
                this.sstcColumn3,
                this.sstcColumn4,
                this.sstcColumn5,
                this.sstcColumn6);

        this.jdbcSearchProfessor.createSearchProfessorTable(
                this.searchProfessorTable,
                this.sptColumn1,
                this.sptColumn2,
                this.sptColumn3,
                this.sptColumn4,
                this.sptColumn5,
                this.sptColumn6,
                this.searchProfessorCourseTable,
                this.spctColumn1,
                this.spctColumn2,
                this.spctColumn3,
                this.spctColumn4,
                this.spctColumn5);

        this.jdbcFindStudentByGpa.createTable(
                this.findStudentGpaTable,
                this.fsgtColumn1,
                this.fsgtColumn2,
                this.fsgtColumn3,
                this.fsgtColumn4,
                this.fsgtColumn5);

        this.jdbcFindProfessorByGpa.createTable(
                this.findProfessorGpaTable,
                this.fpbgColumn1,
                this.fpbgColumn2,
                this.fpbgColumn3,
                this.fpbgColumn4, 
                this.fpbgColumn5);
        this.jdbcSearchStudent.createTableSearchStudentInClass(
                tableCourse,
                colStudentIDSearchCourse,
                colFNSearchCourse,
                colLNSearchCourse,
                colGradeSearchCourse);

        this.jdbcSearchStudent.createTableSearchCourseInQuarter(
                tableCourseFindCourse,
                colCourseIDFindCourse,
                colCourseLabelFindCourse,
                colCourseNameFindCourse,
                colInstructorIDFindCourse,
                colQuarterFindCourse,
                colYearFindCourse);
        this.findStudentGpaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                sliderLabel.setText(String.format("%.2f", newValue));
            }
        });

        this.findProfessorGpaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                sliderLabel2.setText(String.format("%.2f", newValue));
            }
        });

    }

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
            String message = this.jbdcInsert.insertStudent(this.firstNameAS.getText(),
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
            String message = this.jbdcInsert.insertProfessor(this.firstNameAP.getText(),
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
            String message = this.jbdcInsert.insertCourse(this.courseLabelAC.getText(),
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
            String message = this.jbdcInsert.insertGrade(this.courseIDAG.getText(), this.studentIDAG.getText(),
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
    private TableView<Table> searchStudentTable;
    @FXML
    TableColumn<Table, String> sstColumn1 = new TableColumn<>("Student ID");
    @FXML
    TableColumn<Table, String> sstColumn2 = new TableColumn<>("First Name");
    @FXML
    TableColumn<Table, String> sstColumn3 = new TableColumn<>("Last Name");
    @FXML
    TableColumn<Table, String> sstColumn4 = new TableColumn<>("Email");
    @FXML
    TableColumn<Table, String> sstColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    TableColumn<Table, String> sstColumn6 = new TableColumn<>("Major");

    @FXML
    private TableView<Table> searchStudentCourseTable;
    @FXML
    TableColumn<Table, String> sstcColumn1 = new TableColumn<>("Professor ID");
    @FXML
    TableColumn<Table, String> sstcColumn2 = new TableColumn<>("Course Label");
    @FXML
    TableColumn<Table, String> sstcColumn3 = new TableColumn<>("Course Name");
    @FXML
    TableColumn<Table, String> sstcColumn4 = new TableColumn<>("Quarter");
    @FXML
    TableColumn<Table, String> sstcColumn5 = new TableColumn<>("School Year");
    @FXML
    TableColumn<Table, String> sstcColumn6 = new TableColumn<>("Grade");

    @FXML
    private void onSubmitSearchStudent(ActionEvent actionEvent) {
        this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.studentIdSS.getText(), this.errorSearchStudent, "Student ID must contain numbers only.")) {
            String message = this.jdbcSearchStudent.searchStudent(this.studentIdSS.getText(), this.searchStudentTable,
                    this.searchStudentCourseTable);
            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 1, 0));
                this.errorSearchStudent.setText("Searching student with ID: " + this.studentIdSS.getText());
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText(message);
            }
            this.clear();
        }

    }

    // Search Professor
    @FXML
    private Label errorSearchProfessor;
    @FXML
    private TextField professorIdSP;

    @FXML
    private TableView<Professor> searchProfessorTable;
    @FXML
    private TableColumn<Professor, String> sptColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private TableColumn<Professor, String> sptColumn2 = new TableColumn<>("First Name");
    @FXML
    private TableColumn<Professor, String> sptColumn3 = new TableColumn<>("Last Name");
    @FXML
    private TableColumn<Professor, String> sptColumn4 = new TableColumn<>("Email");
    @FXML
    private TableColumn<Professor, String> sptColumn5 = new TableColumn<>("Day of Birth");
    @FXML
    private TableColumn<Professor, String> sptColumn6 = new TableColumn<>("Deparment");

    @FXML
    private TableView<ProfessorCourse> searchProfessorCourseTable;

    @FXML
    private TableColumn<ProfessorCourse, String> spctColumn1 = new TableColumn<>("Course ID");
    @FXML
    private TableColumn<ProfessorCourse, String> spctColumn2 = new TableColumn<>("Course Label");
    @FXML
    private TableColumn<ProfessorCourse, String> spctColumn3 = new TableColumn<>("Course Name");
    @FXML
    private TableColumn<ProfessorCourse, String> spctColumn4 = new TableColumn<>("Quarter");
    @FXML
    private TableColumn<ProfessorCourse, String> spctColumn5 = new TableColumn<>("School Year");

    @FXML
    private void onSubmitSearchProfessor(ActionEvent actionEvent) {
        this.errorSearchProfessor.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.professorIdSP.getText(), this.errorSearchProfessor,
                "Professor ID must contain numbers only.")) {

            String message = this.jdbcSearchProfessor.searchProfessor(this.professorIdSP.getText(),
                    this.searchProfessorTable,
                    this.searchProfessorCourseTable);
            if (message == null) {
                this.errorSearchProfessor.setTextFill(Color.color(0, 1, 0));
                this.errorSearchProfessor.setText("Searching professor with ID: " + this.professorIdSP.getText());
            } else {
                this.errorSearchProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorSearchProfessor.setText(message);
            }
            this.clear();
        }

    }

    // Search Student in class
    @FXML
    private TextField courseIDSSC;
    @FXML
    private Label errorSearchStudentsInClass;
    @FXML
    private TableView<Student> tableCourse;
    @FXML
    private TableColumn<Student, String> colStudentIDSearchCourse = new TableColumn<>("Student Id");
    @FXML
    private TableColumn<Student, String> colFNSearchCourse = new TableColumn<>("First Name");
    @FXML
    private TableColumn<Student, String> colLNSearchCourse = new TableColumn<>("Last Name");
    @FXML
    private TableColumn<Student, String> colGradeSearchCourse = new TableColumn<>("Grade");

    @FXML
    private void onSubmitSearchStudentsInClass(ActionEvent actionEvent) {
        this.errorSearchStudentsInClass.setTextFill(Color.color(1, 0, 0));
        if (idValidator(this.courseIDSSC.getText(), this.errorSearchStudentsInClass,
                "Course ID must contain numbers only.")) {
            String message = this.jdbcSearchStudent.searchStudentInclass(this.courseIDSSC.getText(), tableCourse);
            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 1, 0));
                this.errorSearchStudent.setText("Searching student with Course ID: " + this.courseIDSSC.getText());
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText(message);
            }
            this.clear();
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
    private TableView<CourseSearch> tableCourseFindCourse;
    @FXML
    private TableColumn<CourseSearch, String> colCourseIDFindCourse = new TableColumn<>("Course ID");
    @FXML
    private TableColumn<CourseSearch, String> colCourseLabelFindCourse = new TableColumn<>("Course Label");
    @FXML
    private TableColumn<CourseSearch, String> colCourseNameFindCourse = new TableColumn<>("Course Name");
    @FXML
    private TableColumn<CourseSearch, String> colInstructorIDFindCourse = new TableColumn<>("Instructor ID");
    @FXML
    private TableColumn<CourseSearch, String> colQuarterFindCourse = new TableColumn<>("Quarter");
    @FXML
    private TableColumn<CourseSearch, String> colYearFindCourse = new TableColumn<>("School Year");

    @FXML
    private void onSubmitSearchCourseList(ActionEvent actionEvent) {
        this.errorSearchCourseList.setTextFill(Color.color(1, 0, 0));
        if (choiceBoxPicker(this.quarterSCL.getValue(), this.errorSearchCourseList, "Quarter field is required.") &&
                choiceBoxPicker(this.schoolYearSCL.getValue(), this.errorSearchCourseList,
                        "School year field is required.")) {
            String message = this.jdbcSearchStudent.searchCourseInQuarter(this.schoolYearSCL.getValue(), this.quarterSCL.getValue(), tableCourseFindCourse);
            if (message == null) {
                this.errorSearchStudent.setTextFill(Color.color(0, 1, 0));
                this.errorSearchStudent.setText("Searching course list in " + this.quarterSCL.getValue() + ' ' + this.schoolYearSCL.getValue() +": ");
            } else {
                this.errorSearchStudent.setTextFill(Color.color(1, 0, 0));
                this.errorSearchStudent.setText(message);
            }
            this.clear();
        }

    }

    // Find Students by GPA
    @FXML
    public Label sliderLabel;

    @FXML
    private Label errorFindStudentGpa;

    @FXML
    private Slider findStudentGpaSlider = new Slider(0, 4, 0);

    @FXML
    private ChoiceBox<String> findStudentGpaChoiceBox = new ChoiceBox<>();

    @FXML
    private TableView<StudentGpa> findStudentGpaTable = new TableView<>();
    @FXML
    private TableColumn<StudentGpa, String> fsgtColumn1 = new TableColumn<>("Student ID");
    @FXML
    private TableColumn<StudentGpa, String> fsgtColumn2 = new TableColumn<>("Fist Name");
    @FXML
    private TableColumn<StudentGpa, String> fsgtColumn3 = new TableColumn<>("Last Name");
    @FXML
    private TableColumn<StudentGpa, String> fsgtColumn4 = new TableColumn<>("Major");
    @FXML
    private TableColumn<StudentGpa, String> fsgtColumn5 = new TableColumn<>("GPA");

    @FXML
    private void onSubmitFindStudentGpa(ActionEvent actionEvent) {
        String message = this.jdbcFindStudentByGpa.findStudentByGpa(
                this.findStudentGpaSlider.getValue(),
                this.findStudentGpaChoiceBox.getValue(),
                this.findStudentGpaTable);
        if (message == null) {
            this.errorFindStudentGpa.setTextFill(Color.color(0, 1, 0));
            this.errorFindStudentGpa
                    .setText("Searching student with GPA higher than: " + this.findStudentGpaSlider.getValue());
        } else {
            this.errorFindStudentGpa.setTextFill(Color.color(1, 0, 0));
            this.errorFindStudentGpa.setText(message);
        }
        this.clear();
    }

    // Find Professors by GPA
    @FXML
    public Label sliderLabel2;

    @FXML
    private Label errorFindProfessorGpa;

    @FXML
    private Slider findProfessorGpaSlider = new Slider(0, 4, 0);

    @FXML
    private ChoiceBox<String> findProfessorGpaChoiceBox = new ChoiceBox<>();

    @FXML
    private TableView<ProfessorGpa> findProfessorGpaTable = new TableView<>();
    @FXML
    private TableColumn<ProfessorGpa, String> fpbgColumn1 = new TableColumn<>("Professor ID");
    @FXML
    private TableColumn<ProfessorGpa, String> fpbgColumn2 = new TableColumn<>("Fist Name");
    @FXML
    private TableColumn<ProfessorGpa, String> fpbgColumn3 = new TableColumn<>("Last Name");
    @FXML
    private TableColumn<ProfessorGpa, String> fpbgColumn4 = new TableColumn<>("Department");
    @FXML
    private TableColumn<ProfessorGpa, String> fpbgColumn5 = new TableColumn<>("GPA");

    @FXML
    private void onSubmitFindProfessorGpa(ActionEvent actionEvent) {
        String message = this.jdbcFindProfessorByGpa.findProfessorByGpa(
                this.findProfessorGpaSlider.getValue(),
                this.findProfessorGpaChoiceBox.getValue(),
                this.findProfessorGpaTable);
        if (message == null) {
            this.errorFindProfessorGpa.setTextFill(Color.color(0, 1, 0));
            this.errorFindProfessorGpa
                    .setText("Searching professor with class GPA higher than: " + this.findProfessorGpaSlider.getValue());
        } else {
            this.errorFindProfessorGpa.setTextFill(Color.color(1, 0, 0));
            this.errorFindProfessorGpa.setText(message);
        }
        this.clear();
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
            if (jbdcRemove.removeByStudentID(this.studentIdRS.getText()) == null) {
                this.errorRemoveStudent.setTextFill(Color.color(0, 1, 0));
                this.errorRemoveStudent.setText("Successfully remove student!");
            } else {
                this.errorRemoveStudent.setTextFill(Color.color(0, 1, 0));
                this.errorRemoveStudent.setText("Can't remove student, check your value!    ");
            }
            this.clear();
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
            if (jbdcRemove.removeByProfessorID(this.professorIdRP.getText()) == null) {
                this.errorRemoveProfessor.setTextFill(Color.color(0, 1, 0));
                this.errorRemoveProfessor.setText("Successfully remove professor!");
            } else {
                this.errorRemoveProfessor.setTextFill(Color.color(1, 0, 0));
                this.errorRemoveProfessor.setText("can't remove professor, check your value!");
            }
            this.clear();
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
            if (jbdcRemove.removeByCourseID(this.courseIdRC.getText()) == null) {
                this.errorRemoveCourse.setTextFill(Color.color(0, 1, 0));
                this.errorRemoveCourse.setText("Successfully delete course!");
            } else {
                this.errorRemoveCourse.setTextFill(Color.color(1, 0, 0));
                this.errorRemoveCourse.setText("Can't remove course, check your value!");
            }
            this.clear();
        }
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

    @FXML
    private void onClearFindStudentGpa(ActionEvent actionEvent) {
        this.errorFindStudentGpa.setText("");
        this.clear();
    }

    @FXML
    private void onClearFindProfessorGpa(ActionEvent actionEvent) {
        this.errorFindProfessorGpa.setText("");
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