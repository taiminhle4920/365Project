package com.finalproject.finalproject;

public class Professor {

    private String pid;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private String dob = null;
    private String major = null;

    public Professor() {
    }

    public Professor(String pid, String firstName, String lastName, String email, String dob, String major) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    public String getPid() {
        return pid;
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

    public void setPid(String pid) {
        this.pid = pid;
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