package com.finalproject.finalproject;

public class Student {

    private String sid;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private String dob = null;
    private String major = null;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String grade = null;
    public Student() {
    }
    public Student(String sid, String firstName, String lastName, String grade){
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }
    public Student(String sid, String firstName, String lastName, String email, String dob, String major) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    public String getSid() {
        return sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}