package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.AccountInfo;
import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CreditDebitDTO;
import com.rencia.owen_bank_app.entity.Customer;
import com.rencia.owen_bank_app.repository.CustomerRepository;
import com.rencia.owen_bank_app.utils.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditDebitServiceImpl implements CreditDebitService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BankResponse creditAccount(CreditDebitDTO creditDebitDTO) {
        if (!customerRepository.existsByAccountNumber(creditDebitDTO.getCreditAccountNumber()))
            return BankResponse.builder()
                    .responseCode(ResponseDetails.ACCOUNT_NOT_FOUND_CODE)
                    .responseMessage(ResponseDetails.ACCOUNT_NOT_FOUND_MESSAGE)
                    .accountInfo(null)
                    .build();
        else{
            Customer customer = customerRepository.findByAccountNumber(creditDebitDTO.getCreditAccountNumber());
            customer.setAccountBalance(customer.getAccountBalance().add(creditDebitDTO.getAmount()));
            customerRepository.save(customer);
            return BankResponse.builder()
                    .responseCode(ResponseDetails.ACCOUNT_CREDIT_SUCCESSFUL_CODE)
                    .responseMessage(ResponseDetails.ACCOUNT_CREDIT_SUCCESSFUL_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .fullName(customer.getFirstName() + customer.getLastName())
                            .accountNumber(customer.getAccountNumber())
                            .accountBalance(customer.getAccountBalance())
                            .build())
                    .build();
        }
    }

    @Override
    public BankResponse debitAccount(CreditDebitDTO creditDebitDTO) {
        if (!customerRepository.existsByAccountNumber(creditDebitDTO.getCreditAccountNumber()))
            return BankResponse.builder()
                    .responseCode(ResponseDetails.ACCOUNT_NOT_FOUND_CODE)
                    .responseMessage(ResponseDetails.ACCOUNT_NOT_FOUND_MESSAGE)
                    .accountInfo(null)
                    .build();
        else{
            Customer customer = customerRepository.findByAccountNumber(creditDebitDTO.getCreditAccountNumber());
            if (customer.getAccountBalance().compareTo(creditDebitDTO.getAmount()) < 0)
                return BankResponse.builder()
                        .responseCode(ResponseDetails.INSUFFICIENT_FUND_CODE)
                        .responseMessage(ResponseDetails.INSUFFICIENT_FUND_MESSAGE)
                        .accountInfo(AccountInfo.builder()
                                .fullName(customer.getFirstName() + customer.getLastName())
                                .accountNumber(customer.getAccountNumber())
                                .accountBalance(customer.getAccountBalance())
                                .build())
                        .build();
            else {
                customer.setAccountBalance(customer.getAccountBalance().subtract(creditDebitDTO.getAmount()));
                customerRepository.save(customer);
                return BankResponse.builder()
                        .responseCode(ResponseDetails.ACCOUNT_DEBIT_SUCCESSFUL_CODE)
                        .responseMessage(ResponseDetails.ACCOUNT_DEBIT_SUCCESSFUL_MESSAGE)
                        .accountInfo(AccountInfo.builder()
                                .fullName(customer.getFirstName() + customer.getLastName())
                                .accountNumber(customer.getAccountNumber())
                                .accountBalance(customer.getAccountBalance())
                                .build())
                        .build();
            }
        }
    }
}
