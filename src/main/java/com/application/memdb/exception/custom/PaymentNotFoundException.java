package com.application.memdb.exception.custom;

import lombok.Data;

@Data
public class PaymentNotFoundException extends  RuntimeException{
    private Long id;
    public PaymentNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
