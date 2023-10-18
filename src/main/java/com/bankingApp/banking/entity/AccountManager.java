package com.bankingApp.banking.entity;

import java.util.Map;
import java.util.TreeMap;

public class AccountManager {
    private Map<Long,Account> accList=new TreeMap<Long,Account>();
    private long countOfAccounts = 10000L;
    private long maxAccounts = 99999L;
    private String currency = "Rs.";

    public AccountManager() {
    }

    public void createAccount(String name) {
        try {
            Account newAcc = new Account(name, countOfAccounts++);
            long accID = newAcc.getAccountNo();
            if(accID > maxAccounts) {
                throw new RuntimeException("Sorry, the Bank has reached it's capacity of Account Holders. New account was not created.\n");
            }
            accList.put(accID, newAcc);
            System.out.println(newAccountMsg(name, accID));
        }
        catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        }
        catch (Exception e){
            System.out.println("Error! Sorry, new account was not created.\n");
        }
        return;
    }

    public void depositInAccount(long accID, double amount) {
        try {
            Account userAcc = findByID(accID);
            if(userAcc == null) return;
            double updatedBalance = userAcc.deposit(amount);
            printBalanceMsg(accID, currency, updatedBalance);
            return;
        }
        catch (Exception e){
            System.out.println("Error! Sorry, deposit request could not be processed.\n");
        }
        return;
    }

    public void withdrawFromAccount(long accID, double amount) {
        try {
            Account userAcc = findByID(accID);
            if(userAcc == null) return;
            double updatedBalance = userAcc.withdraw(amount);
            printBalanceMsg(accID, currency, updatedBalance);
            return;
        }
        catch (Exception e){
            System.out.println("Error! Sorry, withdrawal request could not be processed.\n");
        }
        return;
    }

    public void getBalanceOfUser(long accID) {
        try {
            Account userAcc = findByID(accID);
            if(userAcc == null) return;
            double updatedBalance = userAcc.getBalance();
            printBalanceMsg(accID,  currency, updatedBalance);
            return;
        }
        catch (Exception e){
            System.out.println("Error! Sorry, account balance cannot be displayed.\n");
        }
        return;
    }

    public void transferFunds(long srcAccID, long destAccID, double amount) {
        try {
            Account srcAcc = findByID(srcAccID);
            if (srcAcc == null) return;

            Account destAcc = findByID(destAccID);
            if (destAcc == null) return;

            if(srcAcc.withdrawPossible(amount) == true && destAcc.depositPossible(amount) == true) {
                srcAcc.withdraw(amount);
                destAcc.deposit(amount);
                System.out.println("Funds transferred successfully!\n");
            }
            else {
                    System.out.println(" ");
                    return;
            }
        }
        catch (Exception e){
            System.out.println("Error! Sorry, fund transfer could not be performed.\n");
        }
        return;
    }

    private Account findByID(long accID) {
        try {
            Account userAcc = accList.get(accID);
            if(userAcc == null) {
                throw new NullPointerException(String.format("Error! Sorry, the given Account Number %d does not exist!. \n", accID));
            }
            return userAcc;
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String newAccountMsg(String name, long accID) {
        String msg = "Account Created Successfully! \n" + "Account Number : " + String.valueOf(accID) + "\nAccount Holder : " + name + "\n";
        return msg;
    }

    private void printBalanceMsg(long accID, String currency, double updatedBalance) {
        System.out.println(String.format("Account Number %d has balance %s %.2f\n", accID, currency, updatedBalance));
    }
}
