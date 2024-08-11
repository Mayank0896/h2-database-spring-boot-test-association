package com.application.memdb.service;

import com.application.memdb.constant.HeaderConstants;
import com.application.memdb.controller.MainController;
import com.application.memdb.dto.PaymentDTO;
import com.application.memdb.dto.mapper.PaymentMapper;
import com.application.memdb.entity.Payment;
import com.application.memdb.exception.custom.PaymentNotFoundException;
import com.application.memdb.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    private PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Transactional
    public PaymentDTO createPayment(Payment payment, HttpHeaders headers) {
        logger.info("createPayment() called with details: " + payment.toString() + "and headers: " + headers.entrySet().toString());
        /*
        return type of headers.get(HeaderConstants.IDEMPOTENCY_KEY) is List<String>
        so to get string as value in use below method
         */
        String idempotencyKey = headers.getFirst(HeaderConstants.IDEMPOTENCY_KEY);
        logger.info("Identifier called:{}", idempotencyKey);
        Optional<Payment> paymentByIdent = this.paymentRepository.findByIdentifier(idempotencyKey);

        if(paymentByIdent.isPresent()) {
            PaymentDTO paymentDTO = paymentMapper.paymentToPaymentDTO(paymentByIdent.get());
            logger.info("Payment already exists for identifier:{}, so returning it directly without saving again in database", idempotencyKey);
            return paymentDTO;
        }
        payment.addIdentifier(idempotencyKey);
        logger.info("Payment does not exist for identifier:{}, so inserting it in DB",idempotencyKey);

        PaymentDTO paymentDTO = paymentMapper.paymentToPaymentDTO(this.paymentRepository.save(payment));
        logger.info("New Entry is created in Database: {}", paymentDTO.toString());
        return paymentDTO;
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = this.paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found for id:" + id, id));
        return this.paymentMapper.paymentToPaymentDTO(payment);
    }
}
