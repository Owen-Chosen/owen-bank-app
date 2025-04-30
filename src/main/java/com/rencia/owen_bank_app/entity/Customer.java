package com.rencia.owen_bank_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

}
