package com.application.memdb.repository;

import com.application.memdb.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    public Optional<Payment> findByIdentifier(String identifier);
}
