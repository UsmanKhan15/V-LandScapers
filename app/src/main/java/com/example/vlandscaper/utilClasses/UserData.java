package com.example.vlandscaper.utilClasses;

public class UserData {
    String email, userName, date;

    public UserData(String email, String userName, String date) {
        this.email = email;
        this.userName = userName;
        this.date = date;
    }

    public UserData(String userName) {
        this.userName = userName;
    }

    public UserData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
