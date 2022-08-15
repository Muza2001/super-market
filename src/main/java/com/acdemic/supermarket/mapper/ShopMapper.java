package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.ShopRequest;
import com.acdemic.supermarket.dto.response.*;
import com.acdemic.supermarket.entity.Category;
import com.acdemic.supermarket.entity.Shop;
import com.acdemic.supermarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "status", source = "request.status")
    Shop toEntity(ShopRequest request, Category category, User user);

    @Mapping(target = "id", source = "shop.id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "name", source = "shop.name")
    @Mapping(target = "floor", source = "shop.floor")
    @Mapping(target = "status", source = "shop.status")
    @Mapping(target = "category", source = "category")
    ShopResponse toResponse(Shop shop, UserResponse user, CategoryResponse category);
}
