package com.flipkart.dao;

import java.sql.*;

import static com.flipkart.dao.DB.*;

public class InsertOperation {






    public static void main(String[] args) {

        makeConnection();
        PreparedStatement stmt = null;
        try{

            // Step 3 Regiater Driver here and create connection




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
            stmt.close();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
        }
        closeConnection();

    }//end main


}
