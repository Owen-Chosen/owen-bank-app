package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.StatementRequestInfo;
import com.rencia.owen_bank_app.entity.Customer;
import com.rencia.owen_bank_app.entity.Transaction;
import com.rencia.owen_bank_app.repository.CustomerRepository;
import com.rencia.owen_bank_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementServiceImpl implements StatementService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public List<Transaction> getTransactions(StatementRequestInfo statementRequestInfo) {
        Customer customer = customerRepository.findByAccountNumber(statementRequestInfo.getAccountNumber());
        Long cid = customer.getCustomerId();
        List<Transaction> transactionsToReturn = transactionRepository.findAll();
        return transactionsToReturn.stream().
                filter(transaction -> transaction.getCustomer().getCustomerId().equals(cid))
                .toList();
    }
}
