package com.rencia.owen_bank_app.controller;

import com.rencia.owen_bank_app.dto.CreditDebitDTO;
import com.rencia.owen_bank_app.dto.TransferTransactionResponse;
import com.rencia.owen_bank_app.service.CreditDebitService;
import com.rencia.owen_bank_app.service.CustomerService;
import com.rencia.owen_bank_app.service.CustomerServiceImpl;
import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bank")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CreditDebitService creditDebitService;

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

}

