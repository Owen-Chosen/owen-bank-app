package com.rencia.owen_bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferTransactionInfo {

    private String debitFullName;
    private String debitAccountNumber;
    private BigDecimal debitAccountBalance;

    private String creditFullName;
    private String creditAccountNumber;
    private BigDecimal creditAccountBalance;
}
