package com.flipkart.bean;

public class Transaction {
    private Integer studentIndex;
    private float Amount;
    private Integer TransactionID;


    public Transaction(int studentIndex, float amount, int transactionID) {
        this.studentIndex = studentIndex;
        Amount = amount;
        TransactionID = transactionID;
    }

    public Integer getStudentIndex() {
        return studentIndex;
    }

    public void setStudent(Integer studentIndex) {
        this.studentIndex = studentIndex;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }
}
