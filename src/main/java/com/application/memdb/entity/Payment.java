package com.application.memdb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_gen")
    @SequenceGenerator(name = "payment_seq_gen", sequenceName = "payment_seq", allocationSize = 1)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "identifier", unique = true, nullable = false)
    private String identifier;
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Payment() {
    }

    public Payment(Long paymentId, String name, String identifier, LocalDateTime time) {
        this.paymentId = paymentId;
        this.name = name;
        this.identifier = identifier;
        this.time = time;
    }

    public void addIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
