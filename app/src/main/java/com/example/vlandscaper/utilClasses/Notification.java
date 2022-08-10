package com.example.vlandscaper.utilClasses;

public class Notification {
    private String notification;
    private String time;

    public Notification() {
    }

    public Notification(String notification, String time) {
        this.notification = notification;
        this.time = time;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
