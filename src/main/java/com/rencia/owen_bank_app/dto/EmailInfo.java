package com.rencia.owen_bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailInfo {

    private String senderEmail;

    private String recipientEmail;

    private String recipientFullName;

    private String recipientAccountNumber;
}
