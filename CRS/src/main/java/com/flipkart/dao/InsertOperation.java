package com.flipkart.dao;

import java.sql.*;

public class InsertOperation {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TestMySQL";

    static final String USER = "root";
    static final String PASS = "";



    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try{

            // Step 3 Regiater Driver here and create connection

            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            String sql="insert into employee values(?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            // Hard coded data

            int id=630;
            String name="Vabhav";
            String address="Delhi";
            String location="india";
            //Bind values into the parameters.
            stmt.setInt(1, id);  // This would set age
            stmt.setString(2,name);
            stmt.setString(3, address);
            stmt.setString(4, location);
            stmt.executeUpdate();


            sql = "SELECT id, name ,address, location FROM employee";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int eid  = rs.getInt("id");
                String name1 = rs.getString("name");
                String address1 = rs.getString("address");
                String location1 = rs.getString("location");

                //Display values
                System.out.print("ID: " + eid);
                System.out.print(", Age: " + name1);
                System.out.print(", First: " + address1);
                System.out.println(", Last: " + location1);
            }
            //STEP 6: Clean-up environment
            // rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main


}
