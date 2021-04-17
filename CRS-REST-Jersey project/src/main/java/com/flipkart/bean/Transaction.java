package com.flipkart.bean;

import java.time.LocalDateTime;

public class Transaction {
    private Integer studentId;
    private float amount;
    private Integer transactionID;

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String timestamp;


    public Transaction(int studentId, float amount, int transactionID) {
        this.studentId = studentId;
        this.amount = amount;
        this.transactionID = transactionID;
        this.timestamp = String.valueOf(LocalDateTime.now());


    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudent(Integer studentIndex) {
        this.studentId = studentIndex;
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
