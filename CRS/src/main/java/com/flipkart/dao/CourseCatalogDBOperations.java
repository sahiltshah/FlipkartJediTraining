package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CourseCatalogDBOperations implements DaoInterface.CourseCatalogSystemDbFunctions {

    public ArrayList<Course> getAllCourses() {
        System.out.println("Get all courses method");
        ArrayList<Course> ans = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_ALL_COURSES;
            stmt = DB.conn.prepareStatement(sql_query);

            rs = stmt.executeQuery();


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

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            return ans;
        }


    }

    public void showAllCourses() {
        System.out.println("Show All Courses method");
        PreparedStatement stmt = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_ALL_COURSES;
            stmt = DB.conn.prepareStatement(sql_query);
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

    public Course getCourseFromCourseId(int pCourseId) {
        System.out.println("GetCourseFromCourseId: " + pCourseId);
        Course ans = new Course();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_COURSE_FROM_COURSEID;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, pCourseId);
            rs = stmt.executeQuery();
            System.out.println(rs);


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
            }
            //STEP 6: Clean-up environment
            stmt.close();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ConnectionNotMadeYetException connectionNotMadeYetException) {
            connectionNotMadeYetException.printStackTrace();
        } catch (Exception exception){
            System.out.println("Exception hit!");
            System.out.println(exception.getMessage());
        }
        finally {
            try { if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            System.out.println("Returning the course from courseId");
            return ans;
        }


    }

    public void modifyCourseCount(Course course){
        System.out.println("modifyCourseCount method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_COUNT;
            stmt = DB.conn.prepareStatement(sql_query);
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

    public void addFaculty(int professorId,int courseId){
        System.out.println("addFaculty function");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_FACULTY;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1,professorId);
            stmt.setInt(2,courseId);
            stmt.executeUpdate();

            System.out.println("The course has been alloted to the faculty!");

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

    public ArrayList<Course> getFacultyCourses(int professorId) {
        ArrayList<Course> ans = new ArrayList<>();
        System.out.println("getFacultyCourses method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_FACULTY_COURSES;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, professorId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(new Course(rs.getInt("courseId"),rs.getString("courseName"),rs.getInt("courseStrength"),rs.getFloat("courseCost"),rs.getInt("facultyId")));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }
            return ans;
        }
    }

    public void dropFacultyCourse(int courseId){
        System.out.println("drop Faculty course method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_FACULTY;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, -1);
            stmt.setInt(2, courseId);
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



    public void addCourseMap(CourseMap courseMap) {
        System.out.println("addCourseMap method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_COURSE_MAP;
            stmt = DB.conn.prepareStatement(sql_query);
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

    public void dropCourseMap(CourseMap courseMap) {
        System.out.println("dropCourseMap method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.DROP_COURSE_MAP;
            stmt = DB.conn.prepareStatement(sql_query);
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

    public ArrayList<Integer> getCoursesFromStudent(int studentId) {
        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println("GetStudentCourses method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_STUDENT_COURSES;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("course_id");
                ans.add(courseId);
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }
            return ans;
        }
    }

    public ArrayList<Integer> getStudentsFromCourse(int courseId) {
        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println("getStudentsFromCourse method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_STUDENTS_FROM_COURSE;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, courseId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(rs.getInt("student_id"));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }
            return ans;

        }
    }




    public ArrayList<Grade> fetchGrades(int studentId){
        ArrayList<Grade> ans = new ArrayList<>();
        System.out.println("GetStudentGrades method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_GRADES_FROM_STUDENTID;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(new Grade(rs.getInt("student_id"),rs.getInt("course_id"),rs.getString("grade").charAt(0)));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }
            return ans;
        }

    }

    public void addStudentGrades(ArrayList<Grade> grades){

        System.out.println("addStudentGrades method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_GRADE;
            for(Grade grade : grades){
                stmt = DB.conn.prepareStatement(sql_query);
                stmt.setInt(1,grade.studentId );
                stmt.setInt(2, grade.courseId);
                stmt.setString(3, String.valueOf(grade.grade));
                stmt.executeUpdate();
            }


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

    public ArrayList<Grade> getSubjectGrades(int courseId){
        ArrayList<Grade> ans=new ArrayList<>();

        System.out.println("getSubjectGrades method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_GRADE;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, courseId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(new Grade(rs.getInt("student_id"),rs.getInt("course_id"),rs.getString("grade").charAt(0)));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                System.out.println("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }
            return ans;

        }

    }



}
