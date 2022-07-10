package com.finalproject.finalproject;

public class ProfessorCourse {

    private String cid = null;
    private String courseLabel = null;
    private String courseName = null;
    private String quarter = null;
    private String schoolYear = null;

    public ProfessorCourse() {
    }

    public ProfessorCourse(String cid, String courseLabel, String courseName, String quarter, String schoolYear) {
        this.cid = cid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
    }

    public String getCid() {
        return this.cid;
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

    public void setCid(String cid) {
        this.cid = cid;
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

}