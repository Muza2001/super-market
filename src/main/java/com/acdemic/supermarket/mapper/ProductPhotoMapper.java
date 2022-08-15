package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.ProductPhotoRequest;
import com.acdemic.supermarket.dto.response.ProductPhotoResponse;
import com.acdemic.supermarket.dto.response.ProductResponse;
import com.acdemic.supermarket.entity.Product;
import com.acdemic.supermarket.entity.ProductPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPhotoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    ProductPhoto toEntity(ProductPhotoRequest request, Product product);

    @Mapping(target = "product", source = "productResponse")
    @Mapping(target = "id", source = "productPhoto.id")
    ProductPhotoResponse toResponse(ProductPhoto productPhoto, ProductResponse productResponse);

}
