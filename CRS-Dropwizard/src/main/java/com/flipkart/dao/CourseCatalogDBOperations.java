package com.flipkart.dao;

import com.flipkart.exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Grade;
import com.flipkart.global.GlobalVariables;
import com.flipkart.utils.DBConnection;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CourseCatalogDBOperations implements DaoInterface.CourseCatalogSystemDbFunctions {
    public static final Logger logger = Logger.getLogger(CourseCatalogDBOperations.class);

    public ArrayList<Course> getAllCourses() {
        logger.info("Get all courses method");
        ArrayList<Course> ans = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_ALL_COURSES;
            stmt = x.conn.prepareStatement(sql_query);

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


        } catch (ConnectionNotMadeYetException | SQLException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception ex) {
                logger.debug(ex);
                GlobalVariables.appendException(String.valueOf(ex));
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;
        }


    }

    public void showAllCourses() {
        logger.info("Show All Courses method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_ALL_COURSES;
            stmt = x.conn.prepareStatement(sql_query);
            logger.info("Debug");
            ResultSet rs = stmt.executeQuery();
            logger.info("debug 2");

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int courseStrength = rs.getInt("courseStrength");
                float courseCost = rs.getFloat("courseCost");
                int facultyId = rs.getInt("facultyId");

                //Display values
                logger.info(courseId + " | " + courseName + " | " + courseStrength + " | " + courseCost + " | " + facultyId);
            }
            //STEP 6: Clean-up environment
            stmt.close();


        } catch (ConnectionNotMadeYetException | SQLException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }
    }

    public Course getCourseFromCourseId(int pCourseId) {
        logger.info("GetCourseFromCourseId: " + pCourseId);
        Course ans = new Course("",-1,0,0,-1);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_COURSE_FROM_COURSEID;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, pCourseId);
            rs = stmt.executeQuery();
            logger.info(rs);


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
            logger.info("Exception hit!");
            logger.info(exception.getMessage());
        }
        finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try { if (stmt != null) stmt.close(); } catch (Exception e) {
                logger.error(e.getMessage());
            }
            logger.info("Returning the course from courseId");
            return ans;
        }


    }

    public void modifyCourseCount(Course course){
        logger.info("modifyCourseCount method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_COUNT;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1,course.getCourseStrength());
            stmt.setInt(2,course.getCourseId());
            stmt.executeUpdate();

            logger.info("The course count has been modified!");

        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }

    }

    public void addFaculty(int professorId,int courseId){
        logger.info("addFaculty function");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_FACULTY;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1,professorId);
            stmt.setInt(2,courseId);
            stmt.executeUpdate();

            logger.info("The course has been alloted to the faculty!");

        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }

    }

    public ArrayList<Course> getFacultyCourses(int professorId) {
        ArrayList<Course> ans = new ArrayList<>();
        logger.info("getFacultyCourses method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_FACULTY_COURSES;
            stmt = x.conn.prepareStatement(sql_query);
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
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;
        }
    }

    public void dropFacultyCourse(int courseId){
        logger.info("drop Faculty course method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_COURSE_FACULTY;
            stmt = x.conn.prepareStatement(sql_query);
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
                logger.error(se2.getMessage());
            }
        }

    }



    public void addCourseMap(CourseMap courseMap) {
        logger.info("addCourseMap method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_COURSE_MAP;
            stmt = x.conn.prepareStatement(sql_query);
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
                logger.error(se2.getMessage());
            }

            modifyStudentSubjectCount(courseMap.studentId,getStudentSubjectCount(courseMap.studentId)+1);

        }


    }

    public void dropCourseMap(CourseMap courseMap) {
        logger.info("dropCourseMap method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.DROP_COURSE_MAP;
            stmt = x.conn.prepareStatement(sql_query);
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
                logger.error(se2.getMessage());
            }
            modifyStudentSubjectCount(courseMap.studentId,getStudentSubjectCount(courseMap.studentId)-1);
        }

    }

    public ArrayList<Integer> getCoursesFromStudent(int studentId) {
        ArrayList<Integer> ans = new ArrayList<>();
        logger.info("GetStudentCourses method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_STUDENT_COURSES;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int courseId = rs.getInt("courseId");
                ans.add(courseId);
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;
        }
    }

    public ArrayList<Integer> getStudentsFromCourse(int courseId) {
        ArrayList<Integer> ans = new ArrayList<>();
        logger.info("getStudentsFromCourse method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_STUDENTS_FROM_COURSE;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, courseId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(rs.getInt("studentId"));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;

        }
    }

    public ArrayList<CourseMap> getAllCourseMaps(){
        ArrayList<CourseMap> ans = new ArrayList<>();
        logger.info("get all CourseMaps method");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_ALL_COURSEMAPS;
            stmt = x.conn.prepareStatement(sql_query);
            rs = stmt.executeQuery();

            CourseMap courseMap = new CourseMap(-1,-1);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                courseMap.studentId=rs.getInt("studentId");
                courseMap.courseId=rs.getInt("courseId");
                ans.add(courseMap);
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;

        }

    }




    public ArrayList<Grade> fetchGrades(int studentId){
        ArrayList<Grade> ans = new ArrayList<>();
        logger.info("GetStudentGrades method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_GRADES_FROM_STUDENTID;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(new Grade(rs.getInt("studentId"),rs.getInt("courseId"),rs.getString("grade").charAt(0)));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;
        }

    }

    public void addStudentGrades(ArrayList<Grade> grades){

        logger.info("addStudentGrades method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_GRADE;
            for(Grade grade : grades){
                stmt = x.conn.prepareStatement(sql_query);
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
                logger.error(se2.getMessage());
            }
        }

    }

    public ArrayList<Grade> getSubjectGrades(int courseId){
        ArrayList<Grade> ans=new ArrayList<>();
        DBConnection x= DBConnection.getInstance();
        logger.info("getSubjectGrades method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_GRADE;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, courseId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(new Grade(rs.getInt("studentId"),rs.getInt("courseId"),rs.getString("grade").charAt(0)));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;

        }

    }

    @Override
    public void addCourse(Course course) {
        logger.info("addCourse method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_COURSE;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3,course.getCourseStrength());
            stmt.setFloat(4,course.getCourseCost());
            stmt.setInt(5,course.getFacultyId());
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }

    }

    @Override
    public void deleteCourseFromDB(int courseId) {
        logger.info(" deleteCourseFromDB method");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_COURSE;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1, courseId);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }

    }

    public void modifyStudentSubjectCount(int studentId, int newStudentSubjectCount){
        logger.info("Modifying the student subject count: ");
        PreparedStatement stmt = null;
        DBConnection x= DBConnection.getInstance();

        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_STUDENT;
            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1,newStudentSubjectCount);
            stmt.setInt(2,studentId);

            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }
        }

    }

    public int getStudentSubjectCount(int studentId){

        logger.info("Get student subject count method");
        int ans = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DBConnection x= DBConnection.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_STUDENT_SUBJECT_COUNT;

            stmt = x.conn.prepareStatement(sql_query);
            stmt.setInt(1,studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans = rs.getInt("currentSubjectCount");
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
            return ans;
        }

    }


}
