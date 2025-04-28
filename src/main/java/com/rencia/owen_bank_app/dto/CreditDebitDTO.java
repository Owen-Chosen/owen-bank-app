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
public class CreditDebitDTO {

    private BigDecimal amount;
    private String debitAccountNumber;
    private String creditAccountNumber;
}
