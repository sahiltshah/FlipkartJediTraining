package com.flipkart.dao;

public class SQLQueriesConstants {
    //debitCards table
    public static final String GET_DEBIT_CARD = "SELECT * FROM debitCards WHERE cardNumber=(?)";
    public static final String DEBIT_BALANCE = "UPDATE debitCards SET balance=(?) WHERE cardNumber=(?)";

    //transactions table
    public static final String ADD_TRANSACTION = "INSERT INTO transactions values(?,?,?,?)";


    //courses table
    public static final String GET_ALL_COURSES = "SELECT * FROM courses";
    public static final String GET_COURSE_FROM_COURSEID = "SELECT * FROM courses WHERE courseId=(?)";
    public static final String MODIFY_COURSE_COUNT = "UPDATE courses SET courseStrength=(?) WHERE courseId=(?)";
    public static final String MODIFY_COURSE_FACULTY = "UPDATE courses SET facultyId=(?) WHERE courseId=(?)";
    public static final String GET_FACULTY_COURSES = "SELECT* FROM courses WHERE facultyId = (?)";
    public static final String ADD_COURSE = "INSERT INTO courses VALUES(?,?,?,?)";
    public static final String REMOVE_COURSE = "DELETE FROM courses WHERE courseId=(?)";

    //courseMaps table
    public static final String ADD_COURSE_MAP = "INSERT INTO courseMaps values(?,?)";
    public static final String DROP_COURSE_MAP = "DELETE from courseMaps WHERE (studentId=(?) AND courseId=(?))";
    public static final String GET_STUDENT_COURSES = "SELECT courseId FROM courseMaps WHERE studentId = (?)";
    public static final String GET_STUDENTS_FROM_COURSE = "SELECT studentId FROM courseMaps WHERE courseId = (?)";

    //grades table
    public static final String ADD_GRADE = "INSERT INTO grades values(?,?,?)";
    public static final String GET_GRADE = "SELECT* FROM grades WHERE courseId = (?)";
    public static final String GET_GRADES_FROM_STUDENTID = "SELECT* FROM grades WHERE studentId = (?)";


    //loginDetails table
    public static final String LOGIN_ACCESS =  "SELECT* FROM loginDetails WHERE username=(?)";
    public static final String CHANGE_PASSWORD = "UPDATE loginDetails SET password=(?) WHERE username=(?)";
    public static final String GET_USERNAME_FROM_LOGINDETAILS = "SELECT username FROM loginDetails WHERE username = (?)";
    public static final String ADD_LOGINDETAILS = "INSERT INTO loginDetails values(?,?)";
    public static final String REMOVE_LOGINDETAILS = "DELETE from loginDetails WHERE username=(?)";

    //loginMap table
    public static final String LOGIN_MAP_ACCESS = "SELECT* FROM loginMap WHERE username=(?)";
    public static final String GET_USERNAME_FROM_STUDENTID = "SELECT * FROM loginMap WHERE (id=(?) AND type=1)";
    public static final String ADD_LOGINMAP = "INSERT INTO loginMap values(?,?,?)";
    public static final String REMOVE_LOGINMAP = "DELETE from loginMap WHERE username=(?)";
    public static final String MODIFY_USER_TYPE = "UPDATE loginMap SET type=(?),id=(?) WHERE username=(?)";
    public static final String GET_UNREGISTERED_USERS = "SELECT username FROM loginMap WHERE type=3";

    //notifications table
    public static final String ADD_NOTIFICATION = "INSERT INTO notifications VALUES(?,?,?,?)";
    public static final String GET_NOTIFICATION_USER = "SELECT* FROM notifications WHERE username=(?)";



    //students table
    public static final String ADD_STUDENT = "INSERT INTO students VALUES(?,?)";
    public static final String REMOVE_STUDENT = "DELETE FROM students WHERE rollNumber=(?)";
    public static final String MODIFY_STUDENT = "UPDATE students SET currentSubjectCount=(?) WHERE rollNumber=(?)";
    public static final String GET_STUDENT_SUBJECT_COUNT = "select currentSubjectCount FROM students WHERE rollNumber=(?)";

    //professors table
    public static final String ADD_PROFESSOR = "INSERT INTO professors VALUES(?)";
    public static final String REMOVE_PROFESSOR = "DELETE FROM professors WHERE rollNumber=(?)";
}
