package com.flipkart.service;
import com.flipkart.exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.bean.Notification;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.dao.NotificationSystemDBOperations;
import com.flipkart.global.GlobalVariables;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotificationSystem {

    public static final Logger logger = Logger.getLogger(NotificationSystem.class);

    public void addStudentNotification(String message,int studentId){
        try {
            String username = new AuthenticationOperations().getUserNameFromStudentId(studentId);
            if (username.equals(""))
                throw new UserDoesNotExistException();
            new NotificationSystemDBOperations().addNotification(new Notification(String.valueOf(LocalDateTime.now()), GlobalVariables.globalNotificationId++,message,username));

        } catch (UserDoesNotExistException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }
    }


    public ArrayList<Notification> viewStudentNotifications(int studentId){
        try {
            String username = new AuthenticationOperations().getUserNameFromStudentId(studentId);
            if (username.equals(""))
                throw new UserDoesNotExistException();

            ArrayList<Notification> ans = new NotificationSystemDBOperations().getNotifications(username);
            System.out.println("The notifications are: ");
            for (Notification notification : ans){
                notification.printNotification();
            }

            return ans;


        } catch (UserDoesNotExistException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }

        return null;


    }

}
