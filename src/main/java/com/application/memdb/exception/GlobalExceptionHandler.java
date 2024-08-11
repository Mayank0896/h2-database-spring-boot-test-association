package com.application.memdb.exception;

import com.application.memdb.exception.custom.EmployeeNotFoundException;
import com.application.memdb.exception.custom.OrderNotFoundException;
import com.application.memdb.exception.custom.PaymentNotFoundException;
import com.application.memdb.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> employeeNotFoundHandler(EmployeeNotFoundException exception) {
        logger.warn("EmployeeNotFoundException occurred:" + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Employee with id %d not found!!", exception.getEmployeeId()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundHandler(OrderNotFoundException exception) {
        logger.warn("OrderNotFoundException occurred:" + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Order with id %d not found!!", exception.getOrderId()));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> paymentNotFoundHandler(PaymentNotFoundException exception) {
        logger.warn("PaymentNotFoundException occurred:" + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Payment with id %d not found!!", exception.getId()));
    }
}
