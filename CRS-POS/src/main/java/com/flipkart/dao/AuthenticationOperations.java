package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.SpecialUser;
import com.flipkart.bean.Student;
import com.flipkart.handler.UserHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthenticationOperations implements DaoInterface.AuthenticationSystemDBFunctions {
    public static final Logger logger = LogManager.getLogger(AuthenticationOperations.class);


    public ArrayList<String> loginAccess(String username) {
        logger.info("LoginAccess method");
        PreparedStatement stmt = null;
        ArrayList<String> ans = new ArrayList<>();
        ResultSet rs = null;
        DB db= DB.getInstance();
        try {

            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.LOGIN_ACCESS;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans.add(rs.getString("password"));
            }


            //STEP 6: Clean-up environment
            stmt.close();
            return ans;


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());

            }
            return ans;

        }
    }

    public ArrayList<SpecialUser> loginMapAccess(String username) {
        logger.info("Login Map access method");
        PreparedStatement stmt = null;
        ArrayList<SpecialUser> ans = new ArrayList<>();
        ResultSet rs = null;
        DB db= DB.getInstance();
        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.LOGIN_MAP_ACCESS;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans.add(new SpecialUser(rs.getInt("type"), rs.getInt("id")));
            }

            stmt.close();
            return ans;


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
        }


        return ans;
    }

    public String getUserNameFromStudentId(int studentId){
        logger.info("Get username from StudentId method");
        String ans = "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DB db= DB.getInstance();
        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_USERNAME_FROM_STUDENTID;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans = rs.getString("username");
            }

            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ConnectionNotMadeYetException e) {
            logger.error(e.getMessage());
        } finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                if (stmt != null)
                    stmt.close();

            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
            return ans;
        }
    }

    public void changePassword(String userName, String newPassword) {
        logger.info("change password function");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.CHANGE_PASSWORD;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,newPassword);
            stmt.setString(2,userName);
            stmt.executeUpdate();

            logger.info("The password has been modified!");

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

    public String getSpecificUsername(String username){

        String ans = "";

        logger.info("getSpecific username method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_USERNAME_FROM_LOGINDETAILS;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans = rs.getString("userame");
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());

            }
            return ans;

        }

    }

    public void registerNewUserInDB(String username,String password){
        logger.info("register NewUser method");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_LOGINDETAILS;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
            addNewUserToLoginMap(username);

        }

    }

    public void addNewUserToLoginMap(String username){
        logger.info("addNewUserToLoginMap method");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_LOGINMAP;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,username);
            stmt.setInt(2,-1);
            stmt.setInt(3,3);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
        }

    }

    public void removeUsernameFromLoginMap(String username){
        logger.info("Removing user from loginMap");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_LOGINMAP;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,username);

            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
        }

    }

    public void removeUsernameFromLoginDetails(String username){
        logger.info("Removing user from loginDetails");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_LOGINDETAILS;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,username);

            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
        }

    }

    public void addStudentType(String username,int studentId) {
        logger.info("Modifying the user type");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_USER_TYPE;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setInt(1,1);
            stmt.setInt(2,studentId);
            stmt.setString(3,username);

            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());
            }// nothing we can do
        }

    }

    public ArrayList<String> getUnregisteredUsers() {
        ArrayList<String> ans = new ArrayList<>();
        DB db= DB.getInstance();
        logger.info("getSpecific username that are unregistered method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_UNREGISTERED_USERS;
            stmt = db.conn.prepareStatement(sql_query);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(rs.getString("username"));
            }

            //STEP 6: Clean-up environment


        } catch (ConnectionNotMadeYetException | SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                logger.error("SQL Exception: "+se2.getMessage());

            }
            return ans;

        }


    }


    public void addNewProfessorToLoginMap(String username, int facultyId){
        logger.info("add Professor to loginMap");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_LOGINMAP;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1,username);
            stmt.setInt(2,facultyId);
            stmt.setInt(3,2);
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

    public void addNewStudent(Student student){
        logger.info("add Student to students");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_STUDENT;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setInt(1,student.getRollNumber());
            stmt.setInt(2,student.getCurrentSubjectCount());

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

    @Override
    public void removeStudent(int studentId) {
        logger.info("remove student from Students");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_STUDENT;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setInt(1,studentId);

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


}
