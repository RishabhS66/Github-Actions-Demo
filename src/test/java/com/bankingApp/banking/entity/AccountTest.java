package com.bankingApp.banking.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account testAcc = new Account("Test", 10000);

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void getAccountNo() {
        assertEquals(10000, testAcc.getAccountNo());
    }

    @Test
    void getBalance() {
        assertEquals(0, testAcc.getBalance());
        testAcc.deposit(2000.25);
        assertEquals(2000.25, testAcc.getBalance());
    }

    @Test
    void depositPossible() {
        try {
            testAcc.depositPossible(55000);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("more than the maximum value"));
        }

        try {
            testAcc.depositPossible(499.99);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("less than the minimum value"));
        }

        try {
            assertTrue(testAcc.depositPossible(2001.00));
        } catch (Exception ex) {
            fail();
        }

        testAcc.deposit(49000);
        testAcc.deposit(49000);

        try {
            testAcc.depositPossible(2001.00);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("balance exceed the limit"));
        }

        testAcc.deposit(1000);

        try {
            testAcc.depositPossible(700.00);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("performed 3 deposit"));
        }
    }

    @Test
    void deposit() {
        testAcc.deposit(2000.25);
        assertEquals(51000.25, testAcc.deposit(49000.00));
        assertEquals(51000.25, testAcc.deposit(55000));
        assertEquals(51000.25, testAcc.deposit(499.00));
        assertEquals(51000.25, testAcc.deposit(50000.00));
        assertEquals(60000.25, testAcc.deposit(9000.00));
        assertEquals(60000.25, testAcc.deposit(1000.00));
    }

    @Test
    void withdrawPossible() {
        testAcc.deposit(20000);
        try {
            testAcc.withdrawPossible(55000);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("more than the maximum value"));
        }

        try {
            testAcc.withdrawPossible(999.00);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("less than the minimum value"));
        }

        try {
            assertTrue(testAcc.withdrawPossible(2001.00));
        } catch (Exception ex) {
            fail();
        }

        try {
            testAcc.withdrawPossible(20001.00);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("reduce the balance to beyond minimum value"));
        }

        testAcc.withdraw(2000);
        testAcc.withdraw(2000);
        testAcc.withdraw(2000);

        try {
            testAcc.withdrawPossible(2000.00);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("performed 3 withdrawal"));
        }
    }

    @Test
    void withdraw() {
        testAcc.deposit(20000.00);
        assertEquals(18000.00, testAcc.withdraw(2000.00));
        assertEquals(18000.00, testAcc.withdraw(26000));
        assertEquals(18000.00, testAcc.withdraw(499.00));
        assertEquals(18000.00, testAcc.withdraw(20000));
        assertEquals(9000, testAcc.withdraw(9000.00));
        assertEquals(8000, testAcc.withdraw(1000.00));
        assertEquals(8000, testAcc.withdraw(1000.00));
    }
}