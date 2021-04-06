package com.flipkart.service;

import com.flipkart.Exception.BankingException.DebitCardInvalid;
import com.flipkart.Exception.BankingException.InsufficientBalanceException;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Transaction;
import com.flipkart.dao.AccountingSystemDBOperations;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.dao.DB;

import java.util.ArrayList;

import static com.flipkart.temporaryDB.OldDB.globalTransactionId;

//modules complete all the way down
public class AccountingSystem implements serviceInterface.AccountingSystemFunctions {
    @Override
    public float calculateBill(int studentIndex) {
        float totalAmount =0;
        try{
            CourseCatalogDBOperations courseCatalogDBOperations=new CourseCatalogDBOperations();
            ArrayList<Integer> courseIds = courseCatalogDBOperations.getCoursesFromStudent(studentIndex);
            for(int courseId : courseIds){
                totalAmount += courseCatalogDBOperations.getCourseFromCourseId(courseId).getcourseCost();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalAmount;
    }

    @Override
    public boolean makeTransaction(int studentIndex, DebitCard queryDebitCard,float billAmount) {
        try {
            AccountingSystemDBOperations accountingSystemDBOperations=new AccountingSystemDBOperations();
            DebitCard debitCard = accountingSystemDBOperations.fetchDebitCard(queryDebitCard);
            if(debitCard.getCardNumber().equals(""))
                throw new DebitCardInvalid();
            else{
                System.out.println("Card is valid");
                if(debitCard.getBalance()<billAmount)
                    throw new InsufficientBalanceException(debitCard.getBalance()-billAmount);
                else{
                    System.out.println("Adequate balance exists in your account");

                    Transaction transaction = new Transaction(studentIndex, billAmount, globalTransactionId++);

                    accountingSystemDBOperations.debitBalance(debitCard,debitCard.getBalance()-billAmount);
                    accountingSystemDBOperations.addTransaction(transaction);
                    System.out.println("Transaction process in DB complete");
                    new NotificationSystem().addStudentNotification("Paid fees: "+transaction.getAmount()+"  # "+transaction.getTransactionID(),studentIndex);

                }
            }
        } catch (InsufficientBalanceException ex) {
            System.out.println("You are short by the amount: " + ex.getAmount());
        } catch (DebitCardInvalid debitCardInvalid) {
            debitCardInvalid.printStackTrace();
        }
        return false;
    }
}
