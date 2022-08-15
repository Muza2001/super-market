package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.response.OrderDetailsResponse;
import com.acdemic.supermarket.dto.response.OrderResponse;
import com.acdemic.supermarket.entity.Order;
import com.acdemic.supermarket.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "orderDetails", source = "details")
    @Mapping(target = "status", ignore = true)
    Order toEntity(Integer productOfCount, Double quantity, Set<OrderDetails> details);

    @Mapping(target = "products", source = "products")
    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "quantity", source = "order.quantity")
    @Mapping(target = "productCount", source = "order.productCount")
    @Mapping(target = "status", source = "order.status")
    OrderResponse toResponse(Order order,  Set<OrderDetailsResponse> products);

}
