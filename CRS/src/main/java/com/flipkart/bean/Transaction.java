package com.flipkart.bean;

public class Transaction {
    private Integer studentIndex;
    private float amount;
    private Integer transactionID;


    public Transaction(int studentIndex, float amount, int transactionID) {
        this.studentIndex = studentIndex;
        this.amount = amount;
        this.transactionID = transactionID;
    }

    public Integer getStudentIndex() {
        return studentIndex;
    }

    public void setStudent(Integer studentIndex) {
        this.studentIndex = studentIndex;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
}
