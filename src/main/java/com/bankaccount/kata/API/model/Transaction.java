package com.bankaccount.kata.API.model;

import java.time.LocalDateTime;

public class Transaction {

    private OperationEnum typeOperation;
    
    private LocalDateTime transactionDate;
    
    private double amount;
    
    private double balanceAfterOperation;

	public Transaction(OperationEnum typeOperation, LocalDateTime transactionDate,
			double amount, double balanceAfterOperation) {
		super();
		this.typeOperation = typeOperation;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.balanceAfterOperation = balanceAfterOperation;
	}

	public OperationEnum getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(OperationEnum typeOperation) {
		this.typeOperation = typeOperation;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalanceAfterOperation() {
		return balanceAfterOperation;
	}

	public void setBalanceAfterOperation(double balanceAfterOperation) {
		this.balanceAfterOperation = balanceAfterOperation;
	}
	
    @Override
	public String toString() {
		return "Transaction [typeOperation=" + typeOperation + ", transactionDate=" + transactionDate + ", amount="
				+ amount + ", balanceAfterOperation=" + balanceAfterOperation + "]";
	}
}
