package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.PaymentRequest;
import com.acdemic.supermarket.dto.response.OrderResponse;
import com.acdemic.supermarket.dto.response.PaymentResponse;
import com.acdemic.supermarket.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "order", source = "orderResponse")
    @Mapping(target = "id", source = "payment.id")
    @Mapping(target = "totalPrice", source = "payment.totalPrice")
    @Mapping(target = "status", source = "payment.status")
    PaymentResponse toResponse(Payment payment, OrderResponse orderResponse);

}
