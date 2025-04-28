package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CreditDebitDTO;
import com.rencia.owen_bank_app.dto.TransferTransactionInfo;
import com.rencia.owen_bank_app.dto.TransferTransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreditDebitService {

    BankResponse creditAccount (CreditDebitDTO creditDebitDTO);
    BankResponse debitAccount (CreditDebitDTO creditDebitDTO);
    TransferTransactionResponse intraBankTransfer (CreditDebitDTO creditDebitDTO);
}
