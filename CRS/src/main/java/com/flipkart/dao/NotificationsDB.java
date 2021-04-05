package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public class NotificationsDB implements daoInterface.NotificationsDBFunctions {

    @Override
    public boolean writeNotification(String message, Student recipient) {
        return false;
    }

    @Override
    public boolean writeNotification(String message, Professor recipient) {
        return false;
    }

    @Override
    public void retrieveNotifications(Student student) {

    }

    @Override
    public void retrieveNotifications(Professor professor) {

    }
}
