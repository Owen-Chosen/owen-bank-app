package com.rencia.owen_bank_app.controller;

import com.rencia.owen_bank_app.service.CustomerServiceImpl;
import com.rencia.owen_bank_app.utils.BankResponse;
import com.rencia.owen_bank_app.utils.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("/create")
    public BankResponse createCustomerAccount (@RequestBody CustomerInfo customerInfo) {

        return customerServiceImpl.createCustomerAccount(customerInfo);
    }
}
