package com.finalproject.finalproject;

public class StudentGpa {

    private String sid;
    private String firstName = null;
    private String lastName = null;
    private String major = null;
    private String gpa = null;

    public StudentGpa() {
    }

    public StudentGpa(String sid, String firstName, String lastName, String major, String gpa) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.gpa = gpa;
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

    public String getMajor() {
        return major;
    }

    public String getGpa() {
        return gpa;
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

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

}