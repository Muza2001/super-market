package com.acdemic.supermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementResponse {

    private Long id;

    private String title;

    private String caption;

    private String status;

    private Instant created_at;

}
