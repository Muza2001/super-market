package com.acdemic.supermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPhotoResponse {

    private Long id;

    private ProductResponse product;

    private String key;

    private String originName;

    private String path;

    private Long size;

    private String extension;
}
