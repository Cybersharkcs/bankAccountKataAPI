package com.bankaccount.kata.API.service;

import com.bankaccount.kata.API.dao.BankDao;
import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.mapper.IAccountStatementMapper;
import com.bankaccount.kata.API.model.Account;
import com.bankaccount.kata.API.model.Customer;
import com.bankaccount.kata.API.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

public class BankServiceTest {

    @Mock
    private BankDao bankDao;

    @Mock
    private IAccountStatementMapper accountMapper;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShouldCallDaoDeposit() throws IncorrectAmountException {
        Account account = new Account(0L, new Customer("Maxime"));
        account.getListTransaction().add(new Transaction(null, null,1000.0, 1000.0));
        when(bankDao.deposit(1000L)).thenReturn(account);
        when(accountMapper.accountToAccountStatement(account)).thenReturn(new AccountStatement(LocalDateTime.now(), 0, 0));
        bankService.deposit(1000L);
        verify(bankDao, times(1)).deposit(1000L);
    }

    @Test
    void testShouldCallDaoWithdraw() throws IncorrectAmountException, InsufficientFoundException {
        Account account = new Account(0L, new Customer("Maxime"));
        account.getListTransaction().add(new Transaction(null, null,1000.0, 1000.0));
        when(bankDao.withdraw(500L)).thenReturn(account);
        when(accountMapper.accountToAccountStatement(account)).thenReturn(new AccountStatement(LocalDateTime.now(), 0, 0));
        bankService.withdraw(500L);
        verify(bankDao, times(1)).withdraw(500L);
    }

    @Test
    void testShouldReturnStatementFromDao() {
        List<Transaction> listTransaction = List.of(new Transaction(null, null,1000.0, 1000.0));
        when(bankDao.getStatements()).thenReturn(listTransaction);
        when(accountMapper.listTransactionToListAccountStatement(listTransaction)).thenReturn(List.of(new AccountStatement(LocalDateTime.now(), 0, 0)));
        List<AccountStatement> statement = bankService.getStatement();
        assertEquals(1, statement.size());
        verify(bankDao, times(1)).getStatements();
    }

    @Test
    void testShouldThrowExceptionWithInvalidAmount() throws IncorrectAmountException {
        doThrow(new IncorrectAmountException("Amount must be superior to zero."))
                .when(bankDao).deposit(-100.0);
        assertThrows(IncorrectAmountException.class, () -> bankService.deposit(-100.0));
    }

    @Test
    void testShouldThrowExceptionWithInsufficientFunds() throws IncorrectAmountException, InsufficientFoundException {
        doThrow(new InsufficientFoundException("Insufficient founds."))
                .when(bankDao).withdraw(1000.0);
        assertThrows(InsufficientFoundException.class, () -> bankService.withdraw(1000.0));
    }
}
