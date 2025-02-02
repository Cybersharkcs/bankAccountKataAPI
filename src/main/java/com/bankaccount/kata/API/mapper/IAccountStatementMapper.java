package com.bankaccount.kata.API.mapper;

import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.model.Account;
import com.bankaccount.kata.API.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IAccountStatementMapper {
    IAccountStatementMapper INSTANCE = Mappers.getMapper(IAccountStatementMapper.class);

    default AccountStatement accountToAccountStatement(Account account) {
        if(account == null || account.getListTransaction() == null || account.getListTransaction().isEmpty()){
            return null;
        }
        Transaction transaction = account.getListTransaction().getLast();
        return transactionToAccountStatement(transaction);
    }

    default AccountStatement transactionToAccountStatement(Transaction transaction) {
        if(transaction == null) {
            return null;
        }
        return new AccountStatement(transaction.getTransactionDate(), transaction.getAmount(), transaction.getBalanceAfterOperation());
    }

    default List<AccountStatement> listTransactionToListAccountStatement(List<Transaction> listTransaction) {
        if (listTransaction == null || listTransaction.isEmpty()) {
            return null;
        }
        return listTransaction.stream().map(this::transactionToAccountStatement).
                sorted(Comparator.comparing(AccountStatement::getDate).reversed()).toList();
    }
}
