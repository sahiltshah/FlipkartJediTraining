package com.flipkart.utils;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DB {
    public static String JDBC_DRIVER;
    public static String DB_URL;

    public static String USER;
    public static String PASS;

    public static Connection conn = null;

    private static DB single_instance = null;

    public static final Logger logger = LogManager.getLogger(DB.class);


    // private constructor restricted to this class itself
    private DB()
    {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/crsDB";
        USER = "root";
        PASS = "";
        makeConnection();
    }

    // static method to create instance of Singleton class
    public static DB getInstance()
    {
        if (single_instance == null)
            single_instance = new DB();

        return single_instance;
    }


    public static void makeConnection() {
        logger.info("make connection method");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("Connection has been made");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public void closeConnection() {
        logger.info("Connection has been closed");

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
