package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.OrderDetailsRequest;
import com.acdemic.supermarket.dto.response.OrderDetailsResponse;
import com.acdemic.supermarket.dto.response.ProductResponse;
import com.acdemic.supermarket.entity.OrderDetails;
import com.acdemic.supermarket.entity.Product;
import com.acdemic.supermarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    @Mapping(target = "products", source = "products")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    OrderDetails toEntity(Double price, Double quantity, Product products, User user);

    @Mapping(target = "id", source = "orderDetails.id")
    @Mapping(target = "products", source = "productResponses")
    @Mapping(target = "price", source = "orderDetails.price")
    OrderDetailsResponse toResponse(OrderDetails orderDetails, ProductResponse productResponses);

}
