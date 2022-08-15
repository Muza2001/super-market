package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.RoleRequest;
import com.acdemic.supermarket.dto.response.RoleResponse;
import com.acdemic.supermarket.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role role);

}
