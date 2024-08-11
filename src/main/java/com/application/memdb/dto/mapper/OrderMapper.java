package com.application.memdb.dto.mapper;

import com.application.memdb.dto.OrderDTO;
import com.application.memdb.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    // In interface, fields are static and final by default
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "items", source = "items")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "items", source = "items")
    Order orderDTOToOrder(OrderDTO orderDTO);
}