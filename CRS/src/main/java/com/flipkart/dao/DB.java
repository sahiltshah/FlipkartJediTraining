package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;

import java.sql.*;
import java.util.ArrayList;

import com.flipkart.SQLQueriesConstants;
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




}
