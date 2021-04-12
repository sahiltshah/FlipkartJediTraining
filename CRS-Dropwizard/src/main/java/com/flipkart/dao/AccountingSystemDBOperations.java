package com.flipkart.dao;

import com.flipkart.exception.DbException.ConnectionNotMadeYetException;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Transaction;
import com.flipkart.global.GlobalVariables;
import com.flipkart.utils.DB;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountingSystemDBOperations implements DaoInterface.AccountingSystemDBFunctions {
    public static final Logger logger = Logger.getLogger(AccountingSystemDBOperations.class);

    public DebitCard fetchDebitCard(DebitCard debitCard) {
        DebitCard ans = new DebitCard("");
        logger.info("fetch Debit card method");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DB db= DB.getInstance();
        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.GET_DEBIT_CARD;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(1, debitCard.getCardNumber());
            rs = stmt.executeQuery();


            //STEP 5: Extract data from result set
            while (rs.next()) {

                ans.setBalance(rs.getInt("balance"));
                ans.setCvv(rs.getInt("cvv"));
                ans.setCardNumber(rs.getString("cardNumber"));

            }

            stmt.close();

        } catch (SQLException | ConnectionNotMadeYetException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } finally {
            try { if (rs != null) rs.close();
                logger.info("Closed rs");
            } catch (Exception ex) {
                logger.debug(ex);
                GlobalVariables.appendException(String.valueOf(ex));
            }
            try {
                if (stmt != null)
                    stmt.close();

            } catch (SQLException ex) {
                logger.debug(ex);
                GlobalVariables.appendException(String.valueOf(ex));
            }
            return ans;
        }
    }

    public void debitBalance(DebitCard debitCard,float newBalance){
        logger.info("debitBalance method");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.DEBIT_BALANCE;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setString(2, debitCard.getCardNumber());
            stmt.setFloat(1,newBalance);
            stmt.executeUpdate();

            logger.info("The amount has been debited");

        } catch (ConnectionNotMadeYetException | SQLException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                logger.debug(ex);
                GlobalVariables.appendException(String.valueOf(ex));
            }
        }

    }

    public void addTransaction(Transaction transaction){
        logger.info("add Transaction method");
        PreparedStatement stmt = null;
        DB db= DB.getInstance();

        try {
            if (db.conn == null)
                throw new ConnectionNotMadeYetException();

            String sql_query = SQLQueriesConstants.ADD_TRANSACTION;
            stmt = db.conn.prepareStatement(sql_query);
            stmt.setInt(1, transaction.getTransactionID());
            stmt.setFloat(2, transaction.getAmount());
            stmt.setInt(3,transaction.getStudentId());
            stmt.setString(4,transaction.getTimestamp());
            stmt.executeUpdate();


        } catch (ConnectionNotMadeYetException | SQLException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                logger.debug(ex);
                GlobalVariables.appendException(String.valueOf(ex));
            }
        }

    }
}
