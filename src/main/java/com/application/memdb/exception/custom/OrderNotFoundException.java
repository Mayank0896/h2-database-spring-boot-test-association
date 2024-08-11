package com.application.memdb.exception.custom;
// Always use RuntimeException because it is unchecked exception
public class OrderNotFoundException extends RuntimeException {
    private Long orderId;
    public OrderNotFoundException(String message, Long id) {
        super(message);
        this.orderId = id;
    }

    public Long getOrderId() {
        return orderId;
    }
}
