package com.application.memdb;

import com.application.memdb.constant.HeaderConstants;
import com.application.memdb.dto.PaymentDTO;
import com.application.memdb.dto.mapper.PaymentMapper;
import com.application.memdb.entity.Payment;
import com.application.memdb.exception.custom.PaymentNotFoundException;
import com.application.memdb.repository.PaymentRepository;
import com.application.memdb.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
	@Mock
	private PaymentRepository paymentRepository;
	@InjectMocks
	private PaymentService paymentService;
	private static final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;


	@Test
	void testCreatePaymentWhenPaymentExist() {

		String idempotencyKey = "996bede4-3761-4d2c-bc42-1e5e711f204d";
		Payment payment = new Payment(1002L, "Abhishek Bachan", "996bede4-3761-4d2c-bc42-1e5e711f204d", LocalDateTime.now());
		when(this.paymentRepository.findByIdentifier(idempotencyKey)).thenReturn(Optional.of(payment));
		HttpHeaders headers = new HttpHeaders();
		List<String> list = List.of(idempotencyKey);
		headers.put(HeaderConstants.IDEMPOTENCY_KEY, list);
		PaymentDTO paymentDTO = this.paymentService.createPayment(payment, headers);
		assertNotNull(paymentDTO);
		assertEquals(payment.getName(), paymentDTO.getName());
		assertEquals(payment.getPaymentId(), paymentDTO.getPaymentId());
		assertEquals(payment.getTime(), paymentDTO.getTime());
	}

	@Test
	void testCreatePaymentWhenPaymentNotExist() {
		String idempotencyKey = "996bede4-3761-4d2c-bc42-1e5e711f204d";

		// Mock the repository to return an empty Optional for the identifier check
		when(this.paymentRepository.findByIdentifier(idempotencyKey)).thenReturn(Optional.empty());

		// Prepare headers with the idempotency key
		HttpHeaders headers = new HttpHeaders();
		List<String> list = List.of(idempotencyKey);
		headers.put(HeaderConstants.IDEMPOTENCY_KEY, list);

		// Prepare the payment object
		Payment payment = new Payment(null, "Abhishek Bachan", null, LocalDateTime.now());

		// Mock the repository save method to return a payment with an ID
		Payment paymentSaved = new Payment(1002L, payment.getName(), idempotencyKey, payment.getTime());
		when(this.paymentRepository.save(payment)).thenReturn(paymentSaved);

		// Call the service method
		PaymentDTO paymentDTO = this.paymentService.createPayment(payment, headers);

		// Assert the results
		assertNotNull(paymentDTO, "PaymentDTO should not be null");
		assertNotNull(paymentDTO.getPaymentId(), "PaymentDTO's paymentId should not be null");
		assertEquals(idempotencyKey, paymentDTO.getIdentifier(), "Identifiers should match");
		assertEquals(payment.getTime(), paymentDTO.getTime(), "Times should match");
		assertEquals(payment.getName(), paymentDTO.getName(), "Names should match");
	}

	@Test
	void testGetPaymentById() {
		Long id = 10021L;
		Payment payment = new Payment(id, "Abhishek Bachan", "996bede4-3761-4d2c-bc42-1e5e711f204d", LocalDateTime.now());
		when(this.paymentRepository.findById(id)).thenReturn(Optional.of(payment));
		PaymentDTO paymentDTO = this.paymentService.getPaymentById(id);
		assertNotNull(paymentDTO);
		assertEquals(payment.getName(), paymentDTO.getName());
		assertEquals(payment.getTime(), paymentDTO.getTime());
		assertThrows(PaymentNotFoundException.class, () -> this.paymentService.getPaymentById(-1L));
	}

}
