package com.bankaccount.kata.API;

import com.bankaccount.kata.API.dto.AccountStatement;
import com.bankaccount.kata.API.exception.IncorrectAmountException;
import com.bankaccount.kata.API.exception.InsufficientFoundException;
import com.bankaccount.kata.API.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class BankRS {

    @Autowired
    private IBankService bankService;

    @PostMapping(value = "/withdrawal/{amount}")
    public ResponseEntity<AccountStatement> makeWithdrawal(
            @PathVariable("amount") final double amount) throws IncorrectAmountException, InsufficientFoundException {
        return ResponseEntity.status(200).body(bankService.withdraw(amount));
    }

    @PostMapping(value = "/deposit/{amount}")
    public ResponseEntity<AccountStatement> makeDesposit(
            @PathVariable("amount") final double amount) throws IncorrectAmountException {
        return ResponseEntity.status(200).body(bankService.deposit(amount));
    }

    @GetMapping(value = "/statement")
    public ResponseEntity<List<AccountStatement>> getStatement() {
        return ResponseEntity.status(200).body(bankService.getStatement());
    }
}
