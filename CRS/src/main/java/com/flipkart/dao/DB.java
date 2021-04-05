package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;

import java.sql.*;
import java.util.ArrayList;

import com.flipkart.bean.*;

public class DB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crsDB";

    static final String USER = "root";
    static final String PASS = "";

    static Connection conn = null;


    public static void makeConnection() {
        System.out.println("make connection method");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection has been made");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public static void closeConnection() {
        System.out.println("close connection method");
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //finally block used to close resources

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public static ArrayList<Course> getAllCourses() {
        System.out.println("Get all courses method");
        ArrayList<Course> ans = new ArrayList<>();
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT * FROM courses";
            stmt = conn.prepareStatement(sql_query);

            ResultSet rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int courseStrength = rs.getInt("courseStrength");
                float courseCost = rs.getFloat("courseCost");
                int facultyId = rs.getInt("facultyId");

                ans.add(new Course(courseName, courseId, courseStrength, courseCost, facultyId));
            }

            return ans;
            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }

        return ans;
    }

    public static void showAllCourses() {
        System.out.println("Show All Courses method");
        PreparedStatement stmt = null;
        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT * FROM courses";
            stmt = conn.prepareStatement(sql_query);
            System.out.println("Debug");
            ResultSet rs = stmt.executeQuery();
            System.out.println("debug 2");

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int courseStrength = rs.getInt("courseStrength");
                float courseCost = rs.getFloat("courseCost");
                int facultyId = rs.getInt("facultyId");

                //Display values
                System.out.println(courseId + " | " + courseName + " | " + courseStrength + " | " + courseCost + " | " + facultyId);
            }
            //STEP 6: Clean-up environment
            stmt.close();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }
    }

    public static ArrayList<String> loginAccess(String username) {
        System.out.println("LoginAccess method");
        PreparedStatement stmt = null;
        ArrayList<String> ans = new ArrayList<>();
        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT* FROM loginDetails WHERE username=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans.add(rs.getString("password"));
            }


            //STEP 6: Clean-up environment
            stmt.close();
            return ans;


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        }


        return ans;
    }

    public static ArrayList<SpecialUser> loginMapAccess(String username) {
        System.out.println("Login Map access method");
        PreparedStatement stmt = null;
        ArrayList<SpecialUser> ans = new ArrayList<>();
        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT* FROM loginMap WHERE username=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans.add(new SpecialUser(rs.getInt("type"), rs.getInt("id")));
            }

            stmt.close();
            return ans;


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        }


        return ans;
    }

    public static void addCourseMap(CourseMap courseMap) {
        System.out.println("addCourseMap method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "INSERT INTO courseMaps values(?,?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, courseMap.studentId);
            stmt.setInt(2, courseMap.courseId);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }


    }

    public static void dropCourseMap(CourseMap courseMap) {
        System.out.println("dropCourseMap method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "DELETE from courseMaps WHERE (student_id=(?) AND course_id=(?))";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, courseMap.studentId);
            stmt.setInt(2, courseMap.courseId);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }

    }

    public static ArrayList<Integer> getStudentCourses(int studentId) {
        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println("GetStudentCourses method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT course_id FROM courseMaps WHERE student_id = (?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("course_id");
                ans.add(courseId);
            }

            return ans;
            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do

        }
        return ans;
    }

    public static Course getCourseFromCourseId(int pCourseId) {
        System.out.println("GetCourseFromCourseId");
        Course ans = new Course();
        PreparedStatement stmt = null;
        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT * FROM courses WHERE course_id=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, pCourseId);
            ResultSet rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int courseStrength = rs.getInt("courseStrength");
                float courseCost = rs.getFloat("courseCost");
                int facultyId = rs.getInt("facultyId");

                //Display values
                ans = new Course(courseName, courseId, courseStrength, courseCost, facultyId);

                //STEP 6: Clean-up environment
                stmt.close();


            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ConnectionNotMadeYetException connectionNotMadeYetException) {
            connectionNotMadeYetException.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();

            } catch (SQLException se2) {
            }// nothing we can do
            return ans;
        }


    }

    public static DebitCard fetchDebitCard(DebitCard debitCard) {
        DebitCard ans = new DebitCard("");
        System.out.println("fetch Debit card method");
        PreparedStatement stmt = null;
        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "SELECT * FROM debitCards WHERE cardNumber=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, debitCard.getCardNumber());
            ResultSet rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {

                ans.setBalance(rs.getInt("balance"));
                ans.setCvv(rs.getInt("cvv"));
                ans.setCardNumber(rs.getString("cardNumber"));

                stmt.close();


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ConnectionNotMadeYetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();

            } catch (SQLException se2) {
            }// nothing we can do
            return ans;
        }
    }

    public static void debitBalance(DebitCard debitCard,float newBalance){
        System.out.println("debitBalance method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "UPDATE debitCards SET balance=(?) WHERE cardNumber=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(2, debitCard.getCardNumber());
            stmt.setFloat(1,newBalance);
            stmt.executeUpdate();

            System.out.println("The amount has been debited");

        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }

    }

    public static void addTransaction(Transaction transaction){
        System.out.println("add Transaction method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "INSERT INTO transactions values(?,?,?,?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, transaction.getTransactionID());
            stmt.setFloat(2, transaction.getAmount());
            stmt.setInt(3,transaction.getStudentId());
            stmt.setString(4,transaction.getTimestamp());
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }

    }

    public static void modifyCourseCount(Course course){
        System.out.println("modifyCourseCount method");
        PreparedStatement stmt = null;

        try {
            if (conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = "UPDATE courses SET courseStrength=(?) WHERE courseId=(?)";
            stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1,course.getcourseStrength());
            stmt.setInt(2,course.getcourseId());
            stmt.executeUpdate();

            System.out.println("The course count has been modified!");

        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
        }

    }
}
