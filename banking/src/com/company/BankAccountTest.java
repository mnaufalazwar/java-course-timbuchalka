package com.company;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    private BankAccount account;
    private static int count = 0;

    @BeforeAll
    public static void beforeClass(){
        System.out.println("This excuted before any method test " + count++);
    }

    @BeforeEach
    public void setUp(){
        account = new BankAccount("Naufal", "Azwar", 1000, BankAccount.CHECKING);
        System.out.println("Running setup...");
    }

    @org.junit.jupiter.api.Test
    public void deposit() throws Exception{
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @org.junit.jupiter.api.Test
    public void withdraw_branch() throws Exception{
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @org.junit.jupiter.api.Test
    public void withdraw_notBranch() throws Exception{

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(600.00, false));

    }

    @org.junit.jupiter.api.Test
    public void getBalance_deposit() throws Exception{
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    public void getBalance_withdraw() throws Exception{
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    public void isChecking_true(){
        assertTrue(account.isChecking(), "The account is not checking account");
    }

    @AfterAll
    public static void afterClass(){
        System.out.println("After class");
    }
}