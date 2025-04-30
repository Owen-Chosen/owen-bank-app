package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.*;
import com.rencia.owen_bank_app.entity.Customer;
import com.rencia.owen_bank_app.entity.Transaction;
import com.rencia.owen_bank_app.repository.CustomerRepository;
import com.rencia.owen_bank_app.utils.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
            Set<Transaction> transactions = customer.getTransactions();
            transactions.add(Transaction.builder()
                    .transactionType("credit")
                    .transactionAmount(creditDebitDTO.getAmount())
                    .accountBalance(customer.getAccountBalance().add(creditDebitDTO.getAmount()))
                    .customer(customer)
                    .build());
            customer.setTransactions(transactions);
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
        if (!customerRepository.existsByAccountNumber(creditDebitDTO.getDebitAccountNumber()))
            return BankResponse.builder()
                    .responseCode(ResponseDetails.ACCOUNT_NOT_FOUND_CODE)
                    .responseMessage(ResponseDetails.ACCOUNT_NOT_FOUND_MESSAGE)
                    .accountInfo(null)
                    .build();
        else{
            Customer customer = customerRepository.findByAccountNumber(creditDebitDTO.getDebitAccountNumber());
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
                Set<Transaction> transactions = customer.getTransactions();
                transactions.add(Transaction.builder()
                        .transactionType("debit")
                        .transactionAmount(creditDebitDTO.getAmount())
                        .accountBalance(customer.getAccountBalance().add(creditDebitDTO.getAmount()))
                        .customer(customer)
                        .build());
                customer.setTransactions(transactions);
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

    @Override
    public TransferTransactionResponse intraBankTransfer (CreditDebitDTO creditDebitDTO) {

        if (!customerRepository.existsByAccountNumber(creditDebitDTO.getDebitAccountNumber()) || !customerRepository.existsByAccountNumber(creditDebitDTO.getCreditAccountNumber()))
            return TransferTransactionResponse.builder()
                    .responseCode(ResponseDetails.ACCOUNT_NOT_FOUND_CODE)
                    .responseMessage(ResponseDetails.ACCOUNT_NOT_FOUND_MESSAGE)
                    .transferTransactionInfo(null)
                    .build();
        BankResponse b1 = debitAccount(creditDebitDTO);
        BankResponse b2 = creditAccount(creditDebitDTO);
        return TransferTransactionResponse.builder()
                .responseCode(ResponseDetails.TRANSFER_SUCCESSFUL_CODE)
                .responseMessage(ResponseDetails.TRANSFER_SUCCESSFUL_MESSAGE)
                .transferTransactionInfo(TransferTransactionInfo.builder()
                        .debitFullName(b1.getAccountInfo().getFullName())
                        .debitAccountNumber(b1.getAccountInfo().getAccountNumber())
                        .debitAccountBalance(b1.getAccountInfo().getAccountBalance())
                        .creditFullName(b2.getAccountInfo().getFullName())
                        .creditAccountNumber(b2.getAccountInfo().getAccountNumber())
                        .creditAccountBalance(b2.getAccountInfo().getAccountBalance())
                        .build())
                .build();



    }
}
