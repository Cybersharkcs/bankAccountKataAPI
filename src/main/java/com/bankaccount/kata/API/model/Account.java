package com.bankaccount.kata.API.model;


import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    
    private double balance;
    
    private List<Transaction> listTransaction;
    
    private Customer customer;

	public Account(double balance, Customer customer) {
		super();
		this.balance = balance;
		this.customer = customer;
		this.listTransaction = new ArrayList<>();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(List<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account deposit(double amount) throws IncorrectAmountException {
		if (amount <= 0) {
			throw new IncorrectAmountException("Amount must be superior to zero");
		}
		this.balance += amount;
        this.listTransaction.add(new Transaction(OperationEnum.DEPOSIT, LocalDateTime.now(), amount, this.balance));
		return this;
	}
	
	public Account withdraw(double amount) throws InsufficientFoundException, IncorrectAmountException {
		if (amount <= 0) {
			throw new IncorrectAmountException("Amount must be superior to zero");
		}
		if (amount > this.balance) {
			throw new InsufficientFoundException("Insufficient founds");
		}
        this.balance -= amount;
		this.listTransaction.add(new Transaction(OperationEnum.WITHDRAWAL, LocalDateTime.now(), -amount, this.balance));
		return this;
	}
}
