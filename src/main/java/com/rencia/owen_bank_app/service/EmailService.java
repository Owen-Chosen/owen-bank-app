package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.EmailInfo;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendCreationEmail(EmailInfo emailInfo);
}
