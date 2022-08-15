package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.CategoryRequest;
import com.acdemic.supermarket.dto.response.CategoryResponse;
import com.acdemic.supermarket.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Category toEntity(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "parent", source = "category")
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Category toEntityAndParent(CategoryRequest request, Category category);

    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "name", source = "category.name")
    @Mapping(target = "parent", source = "category.parent.id")
    @Mapping(target = "created_at", source = "category.created_at")
    CategoryResponse toResponse(Category category);

}
