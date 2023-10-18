package com.bankingApp.banking.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TransactionHandlerTest {

    private Account testAcc = new Account("Test", 10000);
    private TransactionHandler testTransactionHandler = new TransactionHandler();

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
    void depositPossible() {
        try {
            testTransactionHandler.depositPossible(55000, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("more than the maximum value"));
        }

        try {
            testTransactionHandler.depositPossible(499.99, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("less than the minimum value"));
        }

        try {
            assertTrue(testTransactionHandler.depositPossible(2001.00, testAcc));
        } catch (Exception ex) {
            fail();
        }

        testAcc.deposit(49000);
        testAcc.deposit(49000);

        try {
            testTransactionHandler.depositPossible(2001.00, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("balance exceed the limit"));
        }

        testTransactionHandler.deposit(1000, testAcc);

        try {
            testTransactionHandler.depositPossible(700.00, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("performed 3 deposit"));
        }
    }

    @Test
    void deposit() {
        testTransactionHandler.deposit(2000.25, testAcc);
        assertEquals(51000.25, testTransactionHandler.deposit(49000.00, testAcc));
        assertEquals(51000.25, testTransactionHandler.deposit(55000, testAcc));
        assertEquals(51000.25, testTransactionHandler.deposit(499.00, testAcc));
        assertEquals(51000.25, testTransactionHandler.deposit(50000.00, testAcc));
        assertEquals(60000.25, testTransactionHandler.deposit(9000.00, testAcc));
        assertEquals(60000.25, testTransactionHandler.deposit(1000.00, testAcc));
    }

    @Test
    void withdrawPossible() {
        testTransactionHandler.deposit(20000, testAcc);
        try {
            testTransactionHandler.withdrawPossible(55000, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("more than the maximum value"));
        }

        try {
            testTransactionHandler.withdrawPossible(999.00, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("less than the minimum value"));
        }

        try {
            assertTrue(testTransactionHandler.withdrawPossible(2001.00, testAcc));
        } catch (Exception ex) {
            fail();
        }

        try {
            testTransactionHandler.withdrawPossible(20001.00, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("reduce the balance to beyond minimum value"));
        }

        testTransactionHandler.withdraw(2000, testAcc);
        testTransactionHandler.withdraw(2000, testAcc);
        testTransactionHandler.withdraw(2000, testAcc);

        try {
            testTransactionHandler.withdrawPossible(2000.00, testAcc);
            fail();
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("performed 3 withdrawal"));
        }
    }

    @Test
    void withdraw() {
        testTransactionHandler.deposit(20000.00, testAcc);
        assertEquals(18000.00, testTransactionHandler.withdraw(2000.00, testAcc));
        assertEquals(18000.00, testTransactionHandler.withdraw(26000, testAcc));
        assertEquals(18000.00, testTransactionHandler.withdraw(499.00, testAcc));
        assertEquals(18000.00, testTransactionHandler.withdraw(20000, testAcc));
        assertEquals(9000, testTransactionHandler.withdraw(9000.00, testAcc));
        assertEquals(8000, testTransactionHandler.withdraw(1000.00, testAcc));
        assertEquals(8000, testTransactionHandler.withdraw(1000.00, testAcc));
    }

}