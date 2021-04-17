package com.flipkart.bean;

import com.flipkart.dao.NotificationSystemDBOperations;

public class Notification {
    public Notification(String timestamp, int notificationId, String message, String username) {
        this.timestamp = timestamp;
        this.notificationId = notificationId;
        this.message = message;
        this.username = username;
    }

    public String timestamp;
    public int notificationId;
    public String message;
    public String username;

    public void printNotification() {
        System.out.println("Time: " + this.timestamp + " Message: " + this.message + " Username: " + this.username + " Notification ID: " + this.notificationId);
    }
}


