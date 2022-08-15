package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.UserRequest;
import com.acdemic.supermarket.dto.response.CashbackResponse;
import com.acdemic.supermarket.dto.response.RoleResponse;
import com.acdemic.supermarket.dto.response.UserResponse;
import com.acdemic.supermarket.entity.Cashback;
import com.acdemic.supermarket.entity.Role;
import com.acdemic.supermarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "cashback", source = "cashback")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    User toEntity(UserRequest request, Role role, Cashback cashback);

    @Mapping(target = "cashback", source = "cashbackResponse")
    @Mapping(target = "role", source = "roleResponse")
    @Mapping(target = "id", source = "user.id")
    UserResponse toResponse(User user, CashbackResponse cashbackResponse, RoleResponse roleResponse);
}
