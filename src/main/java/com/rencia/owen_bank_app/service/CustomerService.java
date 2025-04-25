package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.utils.BankResponse;
import com.rencia.owen_bank_app.utils.CustomerInfo;

public interface CustomerService {

    BankResponse createCustomerAccount (CustomerInfo info);
}
