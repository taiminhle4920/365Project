package com.finalproject.finalproject;

public class StudentCourse {

    private String pid = null;
    private String courseLabel = null;
    private String courseName = null;
    private String quarter = null;
    private String schoolYear = null;
    private String grade = null;

    public StudentCourse() {
    }

    public StudentCourse(String pid, String courseLabel, String courseName, String quarter, String schoolYear,
            String grade) {
        this.pid = pid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
        this.grade = grade;
    }

    public String getPid() {
        return this.pid;
    }

    public String getCourseLabel() {
        return this.courseLabel;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public String getSchoolYear() {
        return this.schoolYear;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setCourseLabel(String courseLabel) {
        this.courseLabel = courseLabel;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}