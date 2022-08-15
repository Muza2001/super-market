package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.ProductRequest;
import com.acdemic.supermarket.dto.response.CategoryResponse;
import com.acdemic.supermarket.dto.response.ProductResponse;
import com.acdemic.supermarket.entity.Category;
import com.acdemic.supermarket.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    @Mapping(target = "name", source = "request.name")
    Product toEntity(ProductRequest request, Category category);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "category", source = "categoryResponse")
    @Mapping(target = "name", source = "product.name")
    ProductResponse toResponse(Product product, CategoryResponse categoryResponse);

}
