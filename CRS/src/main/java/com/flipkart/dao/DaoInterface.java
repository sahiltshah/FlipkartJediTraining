package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Single class to incporporate all the interfaces for DAO
 */
public class DaoInterface {
    interface AccountingSystemDBFunctions{
        /**
         * @param debitCard Query debit card enterred by User
         * @return Entire Debit card details
         */
        public DebitCard fetchDebitCard(DebitCard debitCard);

        /**
         * @param debitCard
         * @param newBalance newBalance = OldBalance - amountToBeReduced
         */
        public void debitBalance(DebitCard debitCard,float newBalance);

        /**
         * @param transaction Adds the transaction to the DB table
         */
        public void addTransaction(Transaction transaction);

    }

    interface AuthenticationSystemDBFunctions{
        public ArrayList<String> loginAccess(String username);

        public ArrayList<SpecialUser> loginMapAccess(String username);

        public String getUserNameFromStudentId(int studentId);

        /**
         * @param userName old Username
         * @param newPassword new Password
         */
        public void changePassword(String userName, String newPassword);

        public String getSpecificUsername(String username);

        public void registerNewUserInDB(String username,String password);

        public void addNewUserToLoginMap(String username);

        /**
         * @param username The username whos tuple details are to be deleted from the table
         */
        public void removeUsernameFromLoginMap(String username);

        /**
         * @param username The username whos tuple details are to be deleted from the table
         */
        public void removeUsernameFromLoginDetails(String username);

        /**
         * @param username Username that is de-facto registered
         * @param studentId The new RollNumber assigned to the student by the Admin
         */
        public void addStudentType(String username,int studentId);

        public ArrayList<String> getUnregisteredUsers();
    }




    interface CourseCatalogSystemDbFunctions{
        public ArrayList<Course> getAllCourses();

        public void showAllCourses();

        /**
         * @param pCourseId the specific courseId we need to fetch the whole course for
         * @return Course mapped to the pCourseId
         */
        public Course getCourseFromCourseId(int pCourseId);

        public void modifyCourseCount(Course course);

        /**
         * @param professorId The Id of the new faculty of the course
         * @param courseId Course ID for the course they will be teaching
         */
        public void addFaculty(int professorId,int courseId);

        public ArrayList<Course> getFacultyCourses(int professorId);

        public void dropFacultyCourse(int courseId);

        public void addCourseMap(CourseMap courseMap);

        /**
         * @param courseMap Implies what linkage between a Student and Course ceases to exist
         */
        public void dropCourseMap(CourseMap courseMap);

        public ArrayList<Integer> getCoursesFromStudent(int studentId);

        public ArrayList<Integer> getStudentsFromCourse(int courseId);

        /**
         * @param studentId The student who wants to see their results
         * @return All the subject's grades for which the grades have been inputted
         */
        public ArrayList<Grade> fetchGrades(int studentId);

        /**
         * @param grades Make all Student Grades course wise
         */
        public void addStudentGrades(ArrayList<Grade> grades);

        /**
         * @param courseId
         * @return Returns all the grades from a specific courseId
         */
        public ArrayList<Grade> getSubjectGrades(int courseId);


}
}
