package com.application.memdb.controller;

import com.application.memdb.api.APIResponse;
import com.application.memdb.api.Status;
import com.application.memdb.dto.OrderDTO;
import com.application.memdb.dto.PaymentDTO;
import com.application.memdb.entity.Employee;
import com.application.memdb.entity.Order;
import com.application.memdb.entity.Payment;
import com.application.memdb.exception.custom.EmployeeNotFoundException;
import com.application.memdb.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private PaymentService paymentService;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/v1/test")
    public ResponseEntity<String> getTestResponse() {
        logger.info("getTestResponse() is called Successfully");
        return ResponseEntity.status(200).body("This is test response");
    }

    @PostMapping("/v1/employee/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = this.employeeService.createEmployee(employee);
        logger.info("createEmployee() called for details:{}" , savedEmployee.toString());
        return ResponseEntity.status(201).body(savedEmployee);
    }

    @GetMapping("/v1/get/employee/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable("id") Long empId) throws EmployeeNotFoundException {
        logger.info("getEmpById() called for id:{}", empId);
        Employee emp = this.employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

     @GetMapping("/v1/get/order/{id}")
     public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        logger.info("getOrderById() called for orderId:{}", orderId);
        Order orderById = this.orderService.getOrderById(orderId);
        return ResponseEntity.ok().body(orderById);
     }

    @GetMapping("/v2/get/order/{id}")
    public ResponseEntity<APIResponse<OrderDTO>> getOrderDTOById(@PathVariable("id") Long orderId) {
        logger.info("getOrderDTOById() called for orderId:" + orderId);
        OrderDTO orderById = this.orderService.getOrderDTOById(orderId);
        APIResponse<OrderDTO> responseBody = new APIResponse<>(Status.CREATED, "Details Found Successfully", orderById);
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/v1/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order orderCreated = this.orderService.createOrder(order);
        logger.info("createOrder() called for details: {}",orderCreated.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreated);
    }

    @PostMapping("/v1/payment/create")
    public ResponseEntity<APIResponse<PaymentDTO>> createPayment(@RequestBody Payment payment, @RequestHeader HttpHeaders headers) {
        PaymentDTO paymentDTO = this.paymentService.createPayment(payment, headers);
        APIResponse<PaymentDTO> record = new APIResponse<>(Status.CREATED, "Successfully created record", paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(record);
    }

    @GetMapping("/v1/get/payment/{id}")
    public ResponseEntity<APIResponse<PaymentDTO>> getPaymentByID(@PathVariable Long id) {
        PaymentDTO paymentByIdDTO = this.paymentService.getPaymentById(id);
        APIResponse<PaymentDTO> response = new APIResponse<>(Status.SUCCESS, "Payment Found", paymentByIdDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
