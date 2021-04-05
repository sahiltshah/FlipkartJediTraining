package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDetails {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TestMySQL";

    static final String USER = "root";
    static final String PASS = "";

    static Connection conn = null;


    static void makeConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
