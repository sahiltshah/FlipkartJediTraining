package com.flipkart.dao.oldDAO;

import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Transaction;


import static com.flipkart.temporaryDB.DB.debitCards;
//modules complete
public class BankDB implements daoInterface.BankFunctions {
    @Override
    public float getBalance(DebitCard queryDebitCard) {
        for(DebitCard d : debitCards) {
            if(queryDebitCard.getCardNumber()== d.getCardNumber())
                return d.getBalance();
        }
        System.out.println(queryDebitCard.getCardNumber()+" card not found");
        return -1;
    }

    @Override
    public boolean debitBalance(DebitCard queryDebitCard, Transaction transaction) {
        for(DebitCard d : debitCards) {
            if(queryDebitCard.getCardNumber()== d.getCardNumber())
            {
                d.setBalance(d.getBalance()-transaction.getAmount());
                return true;
            }
        }
        System.out.println(queryDebitCard.getCardNumber()+" card not found");

        return false;
    }

    @Override
    public boolean addDebitCard(DebitCard debitCard) {

        for(DebitCard d : debitCards){
            if(debitCard.getCardNumber()== debitCard.getCardNumber())
            {
                System.out.println("Card already exists in the system");
                return false;
            }
        }
        debitCards.add(debitCard);
        return true;
    }
}
