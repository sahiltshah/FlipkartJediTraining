package com.flipkart.dao;

import com.flipkart.bean.*;

import java.util.ArrayList;

public class daoInterface {

    static interface CourseCatalogDBFunctions{
        ArrayList<Integer> fetchAvailableCourses();
        boolean selectStudyCourse(CourseMap courseMap);
        boolean deleteStudyCourse(CourseMap courseMap);
        boolean selectTeachCourse(Professor p, Course course);
        boolean deleteTeachCourse(Professor p, Course course);
        boolean addCourseToDb(Course course);
        boolean addCoursePreference(CourseMap courseMap);
        ArrayList<Course> getStudentCourses(int student_index);


    }

    static interface AccountsDBFunctions{
        void confirmPayment(Transaction transaction);
    }

    static interface LoginFunctionsDB{
        SpecialUser login(String username, String password);
        boolean returnType(String username);
        boolean add_student(Student s);
        boolean remove_student(Student s);
        boolean add_professor(Professor p);
        boolean remove_professor(Professor p);
        boolean search_student(Student s);
        boolean search_professor(Professor p);
    }

    static interface  NotificationsDBFunctions{
        boolean writeNotification(String message, Student recipient);
        boolean writeNotification(String message, Professor recipient);

        void retrieveNotifications(Student student);
        void retrieveNotifications(Professor professor);

    }

    static interface BankFunctions{
        float getBalance(DebitCard debitCard);
        boolean debitBalance(DebitCard debitCard,Transaction transaction);
        boolean addDebitCard(DebitCard debitCard);

    }
}
