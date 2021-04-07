package com.flipkart.dao;

import com.flipkart.exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.bean.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationSystemDBOperations {
    public static final Logger logger = LogManager.getLogger(NotificationSystemDBOperations.class);

    public void addNotification(Notification notification){
        logger.info("add Notification method");
        PreparedStatement stmt = null;
        DB x= DB.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_NOTIFICATION;
            stmt = x.conn.prepareStatement(sql_query);
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
                logger.error(se2.getMessage());
            }
        }

    }

    public ArrayList<Notification> getNotifications(String queryUsername){
        logger.info("Get notifications method");
        ArrayList<Notification> ans = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        DB x= DB.getInstance();
        try {
            if (x.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_NOTIFICATION_USER;
            stmt = x.conn.prepareStatement(sql_query);
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
                //logger.info();(message);
                ans.add(new Notification(timestamp,notificationId,message,username));

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


