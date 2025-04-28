package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CreditDebitDTO;
import org.springframework.stereotype.Service;

@Service
public interface CreditDebitService {

    BankResponse creditAccount (CreditDebitDTO creditDebitDTO);
    BankResponse debitAccount (CreditDebitDTO creditDebitDTO);
}
