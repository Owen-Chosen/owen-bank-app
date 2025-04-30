package com.rencia.owen_bank_app.controller;

import com.rencia.owen_bank_app.dto.*;
import com.rencia.owen_bank_app.entity.Transaction;
import com.rencia.owen_bank_app.service.CreditDebitService;
import com.rencia.owen_bank_app.service.CustomerService;
import com.rencia.owen_bank_app.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/bank")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CreditDebitService creditDebitService;

    @Autowired
    StatementService statementService;

    @PostMapping(path = "/create")
    public BankResponse createCustomerAccount (@RequestBody CustomerInfo customerInfo) {
        return customerService.createCustomerAccount(customerInfo);
    }

    @PostMapping("/debit")
    public BankResponse debitCustomerAccount (@RequestBody CreditDebitDTO creditDebitDTO) {
        return creditDebitService.debitAccount(creditDebitDTO);
    }

    @PostMapping("/credit")
    public BankResponse creditCustomerAccount (@RequestBody CreditDebitDTO creditDebitDTO) {
        return creditDebitService.creditAccount(creditDebitDTO);
    }

    @PostMapping("/transfer")
    public TransferTransactionResponse intraBankTransfer (@RequestBody CreditDebitDTO creditDebitDTO) {
        return creditDebitService.intraBankTransfer(creditDebitDTO);
    }

    @GetMapping("/statement")
    private List<Transaction> getStatement(@RequestBody StatementRequestInfo statementRequestInfo){
        return statementService.getTransactions(statementRequestInfo);
    }

}

