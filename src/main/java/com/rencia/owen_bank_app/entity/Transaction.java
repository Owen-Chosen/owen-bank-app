package com.rencia.owen_bank_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "customer"})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    private String transactionType;

    private BigDecimal transactionAmount;

    private BigDecimal accountBalance;

    @CreationTimestamp
    private LocalDate transactionDate;

    @CreationTimestamp
    private LocalTime transactionTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;


}
