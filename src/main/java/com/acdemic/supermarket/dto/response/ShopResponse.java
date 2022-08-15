package com.acdemic.supermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopResponse {

    private Long id;

    private String name;

    private Integer floor;

    private String status;

    private UserResponse user;

    private CategoryResponse category;

}
