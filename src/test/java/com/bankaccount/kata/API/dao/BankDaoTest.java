package com.bankaccount.kata.API.dao;

import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankDaoTest {
    private BankDao bankDao;

    @BeforeEach
    void setUp() {
        bankDao = new BankDao();
    }

    @Test
    void testDepositSaveMoney() throws IncorrectAmountException {
        Account account = bankDao.deposit(1000L);
        assertEquals(1500L, account.getBalance());
        assertEquals(1, account.getListTransaction().size());
    }

    @Test
    void testDepositSaveMoneyIncorrectAmountNegative() {
        Throwable exception = assertThrows(IncorrectAmountException.class, () -> bankDao.deposit(-1000L));
        assertEquals("Amount must be superior to zero", exception.getMessage());
    }

    @Test
    void testDepositSaveMoneyIncorrectAmountZero() {
        Throwable exception = assertThrows(IncorrectAmountException.class, () -> bankDao.deposit(0L));
        assertEquals("Amount must be superior to zero", exception.getMessage());
    }

    @Test
    void testWithdrawal() throws InsufficientFoundException, IncorrectAmountException {
        Account account = bankDao.withdraw(250L);
        assertEquals(250L, account.getBalance());
        assertEquals(1, account.getListTransaction().size());
    }

    @Test
    void testExactWithdrawal() throws InsufficientFoundException, IncorrectAmountException {
        Account account = bankDao.withdraw(500L);
        assertEquals(0L, account.getBalance());
        assertEquals(1, account.getListTransaction().size());
    }

    @Test
    void testWithdrawalInsufficientFoundPositive() {
        Throwable exception = assertThrows(InsufficientFoundException.class, () -> bankDao.withdraw(1000L));
        assertEquals("Insufficient founds", exception.getMessage());
    }

    @Test
    void testWithdrawalInsufficientFoundZero() {
        Throwable exception = assertThrows(IncorrectAmountException.class, () -> bankDao.withdraw(0L));
        assertEquals("Amount must be superior to zero", exception.getMessage());
    }

    @Test
    void testAccountStatementAfterMultipleDeposit() throws IncorrectAmountException {
        Account account = bankDao.deposit(500L);
        assertEquals(1000L, account.getBalance());
        account.deposit(1000L);
        assertEquals(2000L, account.getBalance());
        account.deposit(2000L);
        assertEquals(4000L, account.getBalance());
        assertEquals(3, account.getListTransaction().size());
    }

    @Test
    void testAccountStatementAfterMultipleWithdrawal() throws InsufficientFoundException, IncorrectAmountException {
        Account account = bankDao.deposit(7500L);
        assertEquals(8000L, account.getBalance());
        account.withdraw(1000L);
        assertEquals(7000L, account.getBalance());
        account.withdraw(2000L);
        assertEquals(5000L, account.getBalance());
        account.withdraw(3000L);
        assertEquals(2000L, account.getBalance());
        assertEquals(4, account.getListTransaction().size());
    }
}
