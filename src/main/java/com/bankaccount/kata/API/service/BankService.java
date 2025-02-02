package com.bankaccount.kata.API.service;

import com.bankaccount.kata.API.dao.BankDao;
import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.mapper.IAccountStatementMapper;
import com.bankaccount.kata.API.model.Account;
import com.bankaccount.kata.API.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService implements IBankService {
    @Autowired
    private BankDao bankDao;

    @Autowired
    private IAccountStatementMapper accountMapper;

    @Override
    public AccountStatement deposit(double amount) throws IncorrectAmountException {
        Account account = this.bankDao.deposit(amount);
        return accountMapper.accountToAccountStatement(account);
    }

    @Override
    public AccountStatement withdraw(double amount) throws InsufficientFoundException, IncorrectAmountException {
        Account account = this.bankDao.withdraw(amount);
        return accountMapper.accountToAccountStatement(account);
    }

    @Override
    public List<AccountStatement> getStatement() {
        List<Transaction> listTransactions = this.bankDao.getStatements();
        return accountMapper.listTransactionToListAccountStatement(listTransactions);
    }
}
