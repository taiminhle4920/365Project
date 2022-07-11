package com.finalproject.finalproject;

public class CourseSearch {
    private String cid;
    private String pid;
    private String courseLabel = null;
    private String courseName = null;
    private String quarter = null;
    private String schoolYear = null;

    public CourseSearch(String cid, String courseLabel, String courseName, String pid,String quarter, String schoolYear){
        this.cid = cid;
        this.courseLabel = courseLabel;
        this.courseName = courseName;
        this.pid = pid;
        this.quarter = quarter;
        this.schoolYear = schoolYear;
    }
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCourseLabel() {
        return courseLabel;
    }

    public void setCourseLabel(String courseLabel) {
        this.courseLabel = courseLabel;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }



}
