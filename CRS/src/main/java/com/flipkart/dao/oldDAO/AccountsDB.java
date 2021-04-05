package com.flipkart.dao.oldDAO;

import com.flipkart.bean.Transaction;
import com.flipkart.temporaryDB.OldDB;
//modules complete
public class AccountsDB implements daoInterface.AccountsDBFunctions {
    @Override
    public void confirmPayment(Transaction transaction) {
        OldDB.transactions.add(transaction);

    }
}
