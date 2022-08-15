package com.acdemic.supermarket.dto.response;

import com.acdemic.supermarket.entity.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnnouncementPhotoResponse {

    private Announcement announcement;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;

    private Instant created_at;

}
