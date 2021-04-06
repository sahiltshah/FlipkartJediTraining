package com.flipkart.dao;

import com.flipkart.Exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.SQLQueriesConstants;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountingSystemDBOperations implements DaoInterface.AccountingSystemDBFunctions {

    public DebitCard fetchDebitCard(DebitCard debitCard) {
        DebitCard ans = new DebitCard("");
        System.out.println("fetch Debit card method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_DEBIT_CARD;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(1, debitCard.getCardNumber());
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {

                ans.setBalance(rs.getInt("balance"));
                ans.setCvv(rs.getInt("cvv"));
                ans.setCardNumber(rs.getString("cardNumber"));

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

    public void debitBalance(DebitCard debitCard,float newBalance){
        System.out.println("debitBalance method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.DEBIT_BALANCE;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setString(2, debitCard.getCardNumber());
            stmt.setFloat(1,newBalance);
            stmt.executeUpdate();

            System.out.println("The amount has been debited");

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

    public void addTransaction(Transaction transaction){
        System.out.println("add Transaction method");
        PreparedStatement stmt = null;

        try {
            if (DB.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_TRANSACTION;
            stmt = DB.conn.prepareStatement(sql_query);
            stmt.setInt(1, transaction.getTransactionID());
            stmt.setFloat(2, transaction.getAmount());
            stmt.setInt(3,transaction.getStudentId());
            stmt.setString(4,transaction.getTimestamp());
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
}
