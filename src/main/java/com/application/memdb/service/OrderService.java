package com.application.memdb.service;

import com.application.memdb.dto.OrderDTO;
import com.application.memdb.dto.mapper.OrderMapper;
import com.application.memdb.entity.Order;
import com.application.memdb.exception.custom.OrderNotFoundException;
import com.application.memdb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private static final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public Order createOrder(Order order) {
        Order savedOrder = this.orderRepository.save(order);
        return savedOrder;
    }

    public Order getOrderById(Long orderId) {

        Optional<Order> orderById = this.orderRepository.findById(orderId);
        if(orderById.isEmpty()) {
            // As OrderNotFoundException is a unchecked exception as we are extending RuntimeException class instead of Exception class, throws keyword is not required here, and we don't have to propogate also using throws just like we did for EmployeeNotFoundException
            throw new OrderNotFoundException("Order not found for id: " + orderId, orderId);
        }
        return orderById.get();
    }

    public OrderDTO getOrderDTOById(Long orderId){
        Order orderById = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found for id: " + orderId, orderId));
        return orderMapper.orderToOrderDTO(orderById);
    }
}
