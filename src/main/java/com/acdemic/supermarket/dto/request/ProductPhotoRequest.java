package com.acdemic.supermarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPhotoRequest {

    private Long productId;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;
}
