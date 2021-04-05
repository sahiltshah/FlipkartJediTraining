package com.flipkart.service;

import com.flipkart.Exception.BankingException.InsufficientBalanceException;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Course;
import com.flipkart.bean.Transaction;
import com.flipkart.dao.DB;
import com.flipkart.dao.oldDAO.BankDB;
import com.flipkart.dao.oldDAO.CourseCatalogDB;

import java.util.ArrayList;

import static com.flipkart.temporaryDB.OldDB.globalTransactionId;

//modules complete all the way down
public class AccountingSystem implements serviceInterface.AccountingSystemFunctions {
    @Override
    public float calculateBill(int studentIndex) {
        float totalAmount =0;
        ArrayList<Integer> courseIds = DB.getStudentCourses(studentIndex);
        for(int courseId : courseIds){
            totalAmount += DB.getCourseFromCourseId(courseId).getcourseCost();
        }

        return totalAmount;
    }

    @Override
    public boolean makeTransaction(int studentIndex, DebitCard debitCard,float billAmount) {
        try {

            DebitCard dbCard = DB.fetchDebitCard(debitCard);
            

            BankDB bankDB = new BankDB();
            if (bankDB.getBalance(debitCard) >= billAmount) {

                Transaction transaction = new Transaction(studentIndex, billAmount, globalTransactionId++);
                bankDB.debitBalance(debitCard, transaction);
                return true;
            } else throw new InsufficientBalanceException(billAmount - bankDB.getBalance(debitCard));

        } catch (InsufficientBalanceException ex) {
            System.out.println("You are short by the amount: " + ex.getAmount());
        }
        return false;
    }
}
