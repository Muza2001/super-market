package com.acdemic.supermarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopRequest {

    private String name;

    private Integer floor;

    private String status;

    private Long categoryId;

    private Long userId;

}
