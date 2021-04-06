package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.SpecialUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthenticationOperations implements DaoInterface.AuthenticationSystemDBFunctions {
    public ArrayList<String> loginAccess(String username) {
        System.out.println("LoginAccess method");
        PreparedStatement stmt = null;
        ArrayList<String> ans = new ArrayList<>();
        ResultSet rs = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.LOGIN_ACCESS;
            stmt = DB.conn.prepareStatement(sql_query);
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
            e.printStackTrace();
        }
        finally {
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

    public ArrayList<SpecialUser> loginMapAccess(String username) {
        System.out.println("Login Map access method");
        PreparedStatement stmt = null;
        ArrayList<SpecialUser> ans = new ArrayList<>();
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.LOGIN_MAP_ACCESS;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                ans.add(new SpecialUser(rs.getInt("type"), rs.getInt("id")));
            }

            stmt.close();
            return ans;


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        }
        finally {
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
        }


        return ans;
    }

    public String getUserNameFromStudentId(int studentId){
        System.out.println("Get username from StudentId method");
        String ans = "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_USERNAME_FROM_STUDENTID;
            stmt = DB.conn.prepareStatement(sql_query);
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

    public void changePassword(String userName, String newPassword) {
        System.out.println("change password function");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.CHANGE_PASSWORD;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,newPassword);
            stmt.setString(2,userName);
            stmt.executeUpdate();

            System.out.println("The password has been modified!");

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

    public String getSpecificUsername(String username){

        String ans = "";

        System.out.println("getSpecific username method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_USERNAME_FROM_LOGINDETAILS;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans = rs.getString("userame");
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

    public void registerNewUserInDB(String username,String password){
        System.out.println("register NewUser method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_LOGINDETAILS;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            addNewUserToLoginMap(username);
        }

    }

    public void addNewUserToLoginMap(String username){
        System.out.println("addNewUserToLoginMap method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_LOGINMAP;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,username);
            stmt.setInt(2,-1);
            stmt.setInt(3,3);
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

    public void removeUsernameFromLoginMap(String username){
        System.out.println("Removing user from loginMap");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_LOGINMAP;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,username);

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

    public void removeUsernameFromLoginDetails(String username){
        System.out.println("Removing user from loginDetails");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.REMOVE_LOGINDETAILS;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,username);

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

    public void addStudentType(String username,int studentId) {
        System.out.println("Modifying the user type");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.MODIFY_USER_TYPE;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1,1);
            stmt.setInt(2,studentId);
            stmt.setString(3,username);

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

    public ArrayList<String> getUnregisteredUsers() {
        ArrayList<String> ans = new ArrayList<>();

        System.out.println("getSpecific username that are unregistered method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_UNREGISTERED_USERS;
            stmt = DB.conn.prepareStatement(sql_query);
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                ans.add(rs.getString("userame"));
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
