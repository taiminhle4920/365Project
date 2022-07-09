package com.finalproject.finalproject.Tables;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String major;

    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.dob = "";
        this.major = "";
    }

    public Student(
            String firstName,
            String lastName,
            String email,
            String dob,
            String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    // getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String email() {
        return this.email;
    }

    public String getDob() {
        return this.dob;
    }

    public String getMajor() {
        return this.major;
    }

    // setters
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
