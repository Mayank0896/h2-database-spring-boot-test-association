package com.application.memdb.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private Long itemId;
    private String productName;
    private Integer quantity;
}
