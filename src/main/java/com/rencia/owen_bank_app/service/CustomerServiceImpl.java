package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.entity.Customer;
import com.rencia.owen_bank_app.repository.CustomerRepository;
import com.rencia.owen_bank_app.utils.AccountInfo;
import com.rencia.owen_bank_app.utils.BankResponse;
import com.rencia.owen_bank_app.utils.CustomerInfo;
import com.rencia.owen_bank_app.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BankResponse createCustomerAccount(CustomerInfo info) {

        if (customerRepository.existsByEmail(info.getEmail())) {
            return new BankResponse(
                    Response.ACCOUNT_CREATION_FAILED_CODE,
                    Response.ACCOUNT_CREATION_FAILED_MESSAGE,
                    null);
        }

        AccountInfo newAccountInfo = AccountInfo.builder()
                .fullName(info.getFirstName() +" "+ info.getLastName())
                .accountBalance(BigDecimal.ZERO)
                .accountNumber("2025"+ (int)(999999 * Math.random() + 100000))
                .build();

        Customer newCustomer = Customer.builder()
                .firstName(info.getFirstName())
                .lastName(info.getLastName())
                .address(info.getAddress())
                .email(info.getEmail())
                .gender(info.getGender())
                .phoneNumber(info.getPhoneNumber())
                .stateOfOrigin(info.getStateOfOrigin())
                .alternativePhoneNumber(info.getAlternativePhoneNumber())
                .accountBalance(BigDecimal.ZERO)
                .status(info.getStatus())
                .accountNumber("2025"+ (int)(999999 * Math.random() + 100000))
                .build();

        Customer c = customerRepository.save(newCustomer);

        return new BankResponse(
                Response.ACCOUNT_CREATION_SUCCESS_CODE,
                Response.ACCOUNT_CREATION_SUCCESS_MESSAGE,
                newAccountInfo
        );
    }

}
