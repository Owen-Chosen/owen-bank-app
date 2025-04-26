package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CustomerInfo;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    BankResponse createCustomerAccount (CustomerInfo info);
}
