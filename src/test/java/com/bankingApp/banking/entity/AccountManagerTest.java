package com.bankingApp.banking.entity;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void createAccount() {
        AccountManager testAccMgr = new AccountManager();
        setUpStreams();
        testAccMgr.createAccount("Amit Duggal");
        String result = outContent.toString();
        restoreStreams();
        assertEquals(true, result.indexOf("Account Created Successfully! \nAccount Number : 10000\nAccount Holder : Amit Duggal")!=-1);
    }

    @Test
    void depositInAccount() {
        AccountManager testAccMgr = new AccountManager();
        testAccMgr.createAccount("Amit Duggal");
        long testAccID = 10000;
        long wrongTestAccID = 10001;
        setUpStreams();
        testAccMgr.depositInAccount(testAccID, 2000.00);
        testAccMgr.depositInAccount(wrongTestAccID, 2000.00);
        String result = outContent.toString();
        restoreStreams();
        assertEquals(true, result.indexOf("Account Number 10000 has balance Rs. 2000.00")!=-1);
        assertEquals(true, result.indexOf("Error! Sorry, the given Account Number 10001 does not exist!.")!=-1);
    }

    @Test
    void withdrawFromAccount() {
        AccountManager testAccMgr = new AccountManager();
        testAccMgr.createAccount("Amit Duggal");
        long testAccID = 10000;
        long wrongTestAccID = 10001;
        testAccMgr.depositInAccount(testAccID, 20000.00);
        setUpStreams();
        testAccMgr.withdrawFromAccount(testAccID, 2000.00);
        testAccMgr.withdrawFromAccount(wrongTestAccID, 2000.00);
        String result = outContent.toString();
        restoreStreams();
        assertEquals(true, result.indexOf("Account Number 10000 has balance Rs. 18000.00")!=-1);
        assertEquals(true, result.indexOf("Error! Sorry, the given Account Number 10001 does not exist!.")!=-1);
    }

    @Test
    void getBalanceOfUser() {
        AccountManager testAccMgr = new AccountManager();
        testAccMgr.createAccount("Amit Duggal");
        long testAccID = 10000;
        long wrongTestAccID = 10001;
        testAccMgr.depositInAccount(testAccID, 20000.00);
        setUpStreams();
        testAccMgr.getBalanceOfUser(testAccID);
        testAccMgr.getBalanceOfUser(wrongTestAccID);
        String result = outContent.toString();
        restoreStreams();
        assertEquals(true, result.indexOf("Account Number 10000 has balance Rs. 20000.00")!=-1);
        assertEquals(true, result.indexOf("Error! Sorry, the given Account Number 10001 does not exist!.")!=-1);
    }

    @Test
    void transferFunds() {
        AccountManager testAccMgr = new AccountManager();
        testAccMgr.createAccount("Amit Duggal");
        testAccMgr.createAccount("Aman Sharma");
        long srcTestAccID = 10000;
        long destTestAccID = 10001;
        long wrongTestAccID = 10002;
        testAccMgr.depositInAccount(srcTestAccID, 10000.00);
        testAccMgr.depositInAccount(destTestAccID, 10000.00);
        setUpStreams();
        testAccMgr.transferFunds(srcTestAccID, destTestAccID, 4250.50);
        testAccMgr.transferFunds(srcTestAccID, destTestAccID, 55000);
        testAccMgr.transferFunds(wrongTestAccID, destTestAccID, 4250.50);
        String result = outContent.toString();
        restoreStreams();
        assertEquals(true, result.indexOf("Funds transferred successfully!")!=-1);
        assertEquals(true, result.indexOf("Error! Amount to be withdrawn is more than the maximum value of Rs. 25000.00.")!=-1);
        assertEquals(true, result.indexOf("the given Account Number 10002 does not exist")!=-1);
    }
}