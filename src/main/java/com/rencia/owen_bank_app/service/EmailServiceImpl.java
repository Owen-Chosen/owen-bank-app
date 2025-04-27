package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.EmailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage emailMessage;

    @Override
    public void sendCreationEmail(EmailInfo emailInfo) {

        try {
            emailMessage.setFrom(emailInfo.getSenderEmail());
            emailMessage.setTo(emailInfo.getRecipientEmail());
            emailMessage.setSubject("ACCOUNT CREATION");
            emailMessage.setText("Account Created Successfully \n" +
                    "Account Name: " + emailInfo.getRecipientFullName() + "\n" +
                    "Account Number: " + emailInfo.getRecipientAccountNumber());

            javaMailSender.send(emailMessage);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
