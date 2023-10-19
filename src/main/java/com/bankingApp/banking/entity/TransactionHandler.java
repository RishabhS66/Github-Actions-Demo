package com.bankingApp.banking.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionHandler {
    private static double maxBalance = 100000.00;
    private static double minBalance = 0.00;
    private static double maxDeposit = 50000.00;
    private static double minDeposit = 500.00;
    private static double maxWithdrawal = 25000.00;
    private static double minWithdrawal = 1000.00;
    private String currency = "Rs.";

    public boolean depositPossible(double amount, Account acc) throws Exception {
        try {
            if (amount < minDeposit) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited is less than the minimum value of %s %.2f.", currency, minDeposit));
            }
            if (amount > maxDeposit) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited is more than the maximum value of %s %.2f.", currency, maxDeposit));
            }
            if (acc.getBalance() + amount > maxBalance) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited will make the balance exceed the limit of %s %.2f.", currency, maxBalance));
            }
            if (acc.getNoOfDeposits() == 3) {
                if (acc.getDate().equals(getDateFunction())) {
                    throw new IllegalArgumentException("Not Allowed! You have already performed 3 deposit transactions today!");
                } else {
                    acc.setDate(getDateFunction());
                    acc.setNoOfDeposits(0);
                }
            }

            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public double deposit(double amount, Account acc) {
        try {
            boolean check = depositPossible(amount, acc);

            acc.setNoOfDeposits(acc.getNoOfDeposits()+1);
            acc.setBalance(acc.getBalance()+1*amount);
            return acc.getBalance();
        }
        catch(Exception e) {
            return acc.getBalance();
        }
    }

    public boolean withdrawPossible(double amount, Account acc) throws Exception {
        try {
            if (amount < minWithdrawal) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn is less than the minimum value of %s %.2f.", currency, minWithdrawal));
            }
            if (amount > maxWithdrawal) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn is more than the maximum value of %s %.2f.", currency, maxWithdrawal));
            }
            if (acc.getBalance() - amount < minBalance) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn will reduce the balance to beyond minimum value of %s %.2f.", currency, minBalance));
            }
            if (acc.getNoOfWithdrawals() == 3) {
                if (acc.getDate().equals(getDateFunction())) {
                    throw new IllegalArgumentException("Not Allowed! You have already performed 3 withdrawal transactions today!");
                } else {
                    acc.setDate(getDateFunction());
                    acc.setNoOfWithdrawals(0);
                }
            }

            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public double withdraw(double amount, Account acc) {
        try {
            if (!withdrawPossible(amount, acc)) return acc.getBalance();

            acc.setNoOfWithdrawals(acc.getNoOfWithdrawals()+1);
            acc.setBalance(acc.getBalance()-amount);
            return acc.getBalance();
        }
        catch (Exception e) {
            return acc.getBalance();
        }
    }

    private String getDateFunction() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
