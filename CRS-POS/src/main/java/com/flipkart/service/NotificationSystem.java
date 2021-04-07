package com.flipkart.service;
import com.flipkart.Exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.dao.NotificationSystemDBOperations;
import com.flipkart.temporaryDB.OldDB;
import com.sun.tools.corba.se.idl.constExpr.Not;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotificationSystem {

    public void addStudentNotification(String message,int studentId){
        try {
            String username = new AuthenticationOperations().getUserNameFromStudentId(studentId);
            if (username.equals(""))
                throw new UserDoesNotExistException();
            new NotificationSystemDBOperations().addNotification(new Notification(String.valueOf(LocalDateTime.now()), OldDB.globalNotificationId++,message,username));

        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }
    }


    public void viewStudentNotifications(int studentId){
        try {
            String username = new AuthenticationOperations().getUserNameFromStudentId(studentId);
            if (username.equals(""))
                throw new UserDoesNotExistException();

            ArrayList<Notification> ans = new NotificationSystemDBOperations().getNotifications(username);
            System.out.println("The notifications are: ");
            for (Notification notification : ans){
                notification.printNotification();
            }


        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }


    }

}
