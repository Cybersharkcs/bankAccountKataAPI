package com.bankaccount.kata.API.dao;

import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.model.Account;
import com.bankaccount.kata.API.model.Customer;
import com.bankaccount.kata.API.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankDao {
    // No persistence clear after application restart
    private final Account account;

    private final static Customer CUSTOMER = new Customer("Maxime");

    private final static double INITIAL_AMOUNT = 500L;

    public BankDao() {
        this.account = new Account(INITIAL_AMOUNT, CUSTOMER);
    }

    public Account deposit(double amount) throws IncorrectAmountException {
        return this.account.deposit(amount);
    }

    public Account withdraw(double amount) throws IncorrectAmountException, InsufficientFoundException {
        return this.account.withdraw(amount);
    }

    public List<Transaction> getStatements() {
        return this.account.getListTransaction();
    }
}
