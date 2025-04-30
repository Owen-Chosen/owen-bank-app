package com.rencia.owen_bank_app.repository;

import com.rencia.owen_bank_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByAccountNumber(String accountNumber);

    Customer findByAccountNumber(String accountNumber);

}
