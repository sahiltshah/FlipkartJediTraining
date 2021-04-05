package com.flipkart.service;

import com.flipkart.Exception.BankingException.DebitCardInvalid;
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
    public boolean makeTransaction(int studentIndex, DebitCard queryDebitCard,float billAmount) {
        try {

            DebitCard debitCard = DB.fetchDebitCard(queryDebitCard);
            if(debitCard.getCardNumber().equals(""))
                throw new DebitCardInvalid();
            else{
                System.out.println("Card is valid");
                if(debitCard.getBalance()<billAmount)
                    throw new InsufficientBalanceException(debitCard.getBalance()-billAmount);
                else{
                    System.out.println("Adequte balance exists");
                    Transaction transaction = new Transaction(studentIndex, billAmount, globalTransactionId++);
                    DB.debitBalance(debitCard,debitCard.getBalance()-billAmount);
                    DB.addTransaction(transaction);
                    System.out.println("Transaction process in DB complete");
                    System.out.println("");

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
