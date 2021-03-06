package com.revature.assigments.p0.models;

/**
 * POJO class to represent an Account
 */

public class AppAccount {
    private int id;
    private String accountType;
    private String currency;
    private double balance;

    public AppAccount() { super();}

    public AppAccount(String accountType, String currency, double balance) {
        this.accountType = accountType;
        this.currency = currency;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
