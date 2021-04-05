package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.bean.Transaction;
import com.flipkart.temporaryDB.DB;
//modules complete
public class AccountsDB implements daoInterface.AccountsDBFunctions {
    @Override
    public void confirmPayment(Transaction transaction) {
        DB.transactions.add(transaction);

    }
}
