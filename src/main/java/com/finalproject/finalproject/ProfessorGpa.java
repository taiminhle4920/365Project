package com.finalproject.finalproject;

public class ProfessorGpa {

    private String pid;
    private String firstName = null;
    private String lastName = null;
    private String major = null;
    private String gpa = null;

    public ProfessorGpa() {
    }

    public ProfessorGpa(String pid, String firstName, String lastName, String major, String gpa) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.gpa = gpa;
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

    public String getMajor() {
        return major;
    }

    public String getGpa() {
        return gpa;
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

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

}