package com.revature.assigments.p0.models;

/**
 *  POJO Class to represent a Account's Transaction
 */

public class AppTransaction {
    private int id;
    private String transactionType;
    private double transactionAmount;
    private String transactionDate;

    public AppTransaction() { super();}

    public AppTransaction(String transactionType, double transactionAmount, String transactionDate) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
