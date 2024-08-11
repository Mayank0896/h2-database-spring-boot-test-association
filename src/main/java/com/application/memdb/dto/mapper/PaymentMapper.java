package com.application.memdb.dto.mapper;

import com.application.memdb.dto.PaymentDTO;
import com.application.memdb.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    // @Mapping not required as fields name are same in both classes
    // @Mapping(source = "paymentId", target = "paymentId")
    // @Mapping(source = "name", target = "name")
    // @Mapping(source = "identifier", target = "identifier")
    // @Mapping(source = "time", target = "time")
    PaymentDTO paymentToPaymentDTO(Payment payment);

    // @Mapping(source = "paymentId", target = "paymentId")
    // @Mapping(source = "name", target = "name")
    // @Mapping(source = "identifier", target = "identifier")
    // @Mapping(source = "time", target = "time")
    Payment paymentDTOToPayment(PaymentDTO paymentDTO);
}