package com.application.memdb.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private String customerName;
    private String mobileNumber;
    private LocalDateTime orderTime;
    private List<ItemDTO> items;
}
