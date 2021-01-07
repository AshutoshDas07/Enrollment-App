package com.example.enrollmentapp;

public class UserDetails {
    String country;
    String dob;
    String first_name;
    String gender;
    String hometown;
    String last_name;
    String phone_number;
    String state;
    String telephone_number;
    String timestamp;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getHometown() {
        return hometown;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }


    public UserDetails() {

    }
}
