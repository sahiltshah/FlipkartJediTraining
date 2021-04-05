package com.flipkart.service;

import com.flipkart.Exception.BankingException.InsufficientBalanceException;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;
import com.flipkart.bean.Transaction;
import com.flipkart.dao.BankDB;
import com.flipkart.dao.CourseCatalogDB;

import java.util.ArrayList;

import static com.flipkart.temporaryDB.DB.globalTransactionId;

//modules complete all the way down
public class AccountingSystem implements serviceInterface.AccountingSystemFunctions {
    @Override
    public float calculateBill(int studentIndex) {
        float totalAmount =0;
        CourseCatalogDB courseCatalogDB = new CourseCatalogDB();
        ArrayList<Course> studentCourses = courseCatalogDB.getStudentCourses(studentIndex);
        for(Course s : studentCourses){
            totalAmount+=s.getcourseCost();
        }
        return totalAmount;
    }

    @Override
    public boolean makeTransaction(int studentIndex, DebitCard debitCard,float billAmount) {
        try {
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
