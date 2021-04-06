package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationSystemDBOperations {
    public void addNotification(Notification notification){
        System.out.println("add Notification method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_NOTIFICATION;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1,notification.notificationId);
            stmt.setString(2,notification.timestamp);
            stmt.setString(3,notification.message);
            stmt.setString(4,notification.username);
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

    public ArrayList<Notification> getNotifications(String queryUsername){
        System.out.println("Get notifications method");
        ArrayList<Notification> ans = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_NOTIFICATION_USER;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1,queryUsername);

            rs = stmt.executeQuery();

            String timestamp;
            int notificationId;
            String message;
            String username;

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                notificationId=rs.getInt(1);
                timestamp=rs.getString(2);
                message=rs.getString(3);
                username=rs.getString(4);
                //System.out.println(message);
                ans.add(new Notification(timestamp,notificationId,message,username));

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
}


