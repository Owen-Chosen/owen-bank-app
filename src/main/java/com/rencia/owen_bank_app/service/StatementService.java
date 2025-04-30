package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.StatementRequestInfo;
import com.rencia.owen_bank_app.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface StatementService {

    List<Transaction> getTransactions(StatementRequestInfo statementRequestInfo);
}
