package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.CardRequest;
import com.acdemic.supermarket.dto.response.CardResponse;
import com.acdemic.supermarket.dto.response.UserResponse;
import com.acdemic.supermarket.entity.Card;
import com.acdemic.supermarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "status", source = "request.status")
    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Card toEntity(CardRequest request, User user);

    @Mapping(target = "user", source = "userResponse")
    @Mapping(target = "status", source = "card.status")
    CardResponse toResponse(Card card, UserResponse userResponse);

}
