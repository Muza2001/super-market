package com.acdemic.supermarket.mapper;

import com.acdemic.supermarket.dto.request.AnnouncementPhotoRequest;
import com.acdemic.supermarket.dto.response.AnnouncementPhotoResponse;
import com.acdemic.supermarket.entity.AnnouncementPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnouncementPhotoMapper {

    @Mapping(target = "created_at", expression = "java(java.time.Instant.now())")
    AnnouncementPhoto toEntity(AnnouncementPhotoRequest request);

    AnnouncementPhotoResponse toResponse(AnnouncementPhoto announcementPhoto);

}
