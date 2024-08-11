package com.application.memdb.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long paymentId;
    private String name;
    private String identifier;
    private LocalDateTime time;

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
