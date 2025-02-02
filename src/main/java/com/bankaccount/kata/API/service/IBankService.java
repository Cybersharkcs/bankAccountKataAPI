package com.bankaccount.kata.API.service;

import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;

import java.util.List;

public interface IBankService {
    AccountStatement deposit(double amount) throws IncorrectAmountException;

    AccountStatement withdraw(double amount) throws InsufficientFoundException, IncorrectAmountException;

    List<AccountStatement> getStatement();
}
