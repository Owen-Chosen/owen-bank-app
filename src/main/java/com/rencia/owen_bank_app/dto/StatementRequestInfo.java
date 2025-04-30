package com.rencia.owen_bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementRequestInfo {

    private String accountNumber;

    private LocalDate startDate;

    private LocalDate endDate;
}
