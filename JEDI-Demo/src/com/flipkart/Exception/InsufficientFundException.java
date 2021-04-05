package com.flipkart.Exception;

public class InsufficientFundException extends Exception
{
    private double amount;
    public InsufficientFundException(double amount)
    {
        this.amount = amount;
    }
    public double getAmount()
    {
        return amount;
    }
}
