package com.flipkart.bean;

public class DebitCard {

    private String cardNumber;
    private Integer cvv;
    private String name;
    private float balance;

    public DebitCard(String s) {
        this.cardNumber=s;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void printInputDebitCard(){
        System.out.println("Debit card: " + this.getCardNumber() + this.cvv);
    }

}
