package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.CashbackRequest;
import com.acdemic.supermarket.dto.response.CashbackResponse;
import com.acdemic.supermarket.entity.Cashback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashbackMapper {

    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Cashback toEntity(CashbackRequest request);

    CashbackResponse toResponse(Cashback cashback);

}
