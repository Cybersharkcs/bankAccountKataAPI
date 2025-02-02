package com.bankaccount.kata.API;

import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BankRSTest {
    @Mock
    private BankService bankService;

    @InjectMocks
    private BankRS bankRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDepositShouldReturnSuccessMessage() throws IncorrectAmountException {
        AccountStatement accountStatement = new AccountStatement(LocalDateTime.now(), 500L, 1000L);
        when(bankService.deposit(1000L)).thenReturn(accountStatement);
        ResponseEntity<AccountStatement> response = bankRestController.makeDesposit(1000L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountStatement, response.getBody());
    }

    @Test
    void testWithdrawShouldReturnSuccessMessage() throws IncorrectAmountException, InsufficientFoundException {
        AccountStatement accountStatement = new AccountStatement(LocalDateTime.now(), 500L, 1000L);
        when(bankService.withdraw(500L)).thenReturn(accountStatement);
        ResponseEntity<AccountStatement> response = bankRestController.makeWithdrawal(500L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountStatement, response.getBody());
    }

    @Test
    void testStatementsShouldReturnSuccessMessage() throws IncorrectAmountException, InsufficientFoundException {
        AccountStatement accountStatement = new AccountStatement(LocalDateTime.now(), 500L, 1000L);
        when(bankService.getStatement()).thenReturn(List.of(accountStatement));
        ResponseEntity<List<AccountStatement>> response = bankRestController.getStatement();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(accountStatement), response.getBody());
    }
}
