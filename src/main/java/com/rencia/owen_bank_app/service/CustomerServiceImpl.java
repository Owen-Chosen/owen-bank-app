package com.rencia.owen_bank_app.service;

import com.rencia.owen_bank_app.dto.EmailInfo;
import com.rencia.owen_bank_app.entity.Customer;
import com.rencia.owen_bank_app.repository.CustomerRepository;
import com.rencia.owen_bank_app.dto.AccountInfo;
import com.rencia.owen_bank_app.dto.BankResponse;
import com.rencia.owen_bank_app.dto.CustomerInfo;
import com.rencia.owen_bank_app.utils.ResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public BankResponse createCustomerAccount(CustomerInfo info) {

        if (customerRepository.existsByEmail(info.getEmail())) {
            return new BankResponse(
                    ResponseDetails.ACCOUNT_CREATION_FAILED_CODE,
                    ResponseDetails.ACCOUNT_CREATION_FAILED_MESSAGE,
                    null);
        }

        AccountInfo newAccountInfo = AccountInfo.builder()
                .fullName(info.getFirstName() +" "+ info.getLastName())
                .accountBalance(BigDecimal.ZERO)
                .accountNumber("2025"+ (int)(999999 * Math.random() + 100000))
                .build();

        Customer newCustomer = Customer.builder()
                .firstName(info.getFirstName())
                .lastName(info.getLastName())
                .address(info.getAddress())
                .email(info.getEmail())
//                .password(new BCryptPasswordEncoder().encode(info.getPassword()))
                .password(passwordEncoder.encode(info.getPassword()))
                .password(passwordEncoder.(info.getPassword()))
                .gender(info.getGender())
                .phoneNumber(info.getPhoneNumber())
                .stateOfOrigin(info.getStateOfOrigin())
                .alternativePhoneNumber(info.getAlternativePhoneNumber())
                .accountBalance(BigDecimal.ZERO)
                .status(info.getStatus())
                .accountNumber("2025"+ (int)(999999 * Math.random() + 100000))
                .build();

        Customer c = customerRepository.save(newCustomer);
        EmailInfo emailInfo = EmailInfo.builder()
                .senderEmail(senderEmail)
                .recipientEmail(c.getEmail())
                .recipientFullName(c.getFirstName() + " " + c.getLastName())
                .recipientAccountNumber(c.getAccountNumber())
                .build();
        emailService.sendCreationEmail(emailInfo);

        return BankResponse.builder()
                .responseCode(ResponseDetails.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(ResponseDetails.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(newAccountInfo)
                .build();
    }

}
