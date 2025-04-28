package com.rencia.owen_bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferTransactionResponse {

    private String responseCode;
    private String responseMessage;
    private TransferTransactionInfo transferTransactionInfo;
}
