package com.bankingApp.banking.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private String accountHolder;
    private long accountNo;
    private double balance;
    private int noOfDeposits = 0;
    private int noOfWithdrawals = 0;
    private String currency = "Rs.";
    private String date;
    private TransactionHandler transactionManager = new TransactionHandler();

    public Account(String name, long accountNo) {
        this.accountHolder = name;
        this.accountNo = accountNo;
        this.balance = 0.0;
        this.date = getDateFunction();
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public int getNoOfDeposits() {
        return noOfDeposits;
    }

    public int getNoOfWithdrawals() {
        return noOfWithdrawals;
    }

    public String getDate() {
        return date;
    }

    public void setBalance(double updatedBalance) {
        balance = updatedBalance;
    }

    public void setNoOfDeposits(int updatedDepositCount) {
        noOfDeposits = updatedDepositCount;
    }

    public void setNoOfWithdrawals(int updatedWithdrawalCount) {
        noOfWithdrawals = updatedWithdrawalCount;
    }

    public void setDate(String updatedDate) {
        date = updatedDate;
    }

    private String getDateFunction() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public boolean depositPossible(double amount) throws Exception {
        try {
            return transactionManager.depositPossible(amount, this);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public double deposit(double amount) {
        return transactionManager.deposit(amount, this);
    }

    public boolean withdrawPossible(double amount) throws Exception{
        try {
            return transactionManager.withdrawPossible(amount, this);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public double withdraw(double amount) {
        return transactionManager.withdraw(amount, this);
    }

}
