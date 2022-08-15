package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.AnnouncementRequest;
import com.acdemic.supermarket.dto.response.AnnouncementResponse;
import com.acdemic.supermarket.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    AnnouncementRequest toDto(Announcement announcement);

    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    Announcement toEntity(AnnouncementRequest announcementRequest);

    AnnouncementResponse toResponse(Announcement announcement);

}
