package com.flipkart.dao;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DB {
    public String JDBC_DRIVER;
    public String DB_URL;

    public String USER;
    public String PASS;

    public Connection conn = null;

    private static DB single_instance = null;

    public static final Logger logger = LogManager.getLogger(DB.class);


    // private constructor restricted to this class itself
    private DB()
    {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/crsDB";
        USER = "root";
        PASS = "";
    }

    // static method to create instance of Singleton class
    public static DB getInstance()
    {
        if (single_instance == null)
            single_instance = new DB();

        return single_instance;
    }


    public void makeConnection() {
        logger.info("make connection method");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
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
