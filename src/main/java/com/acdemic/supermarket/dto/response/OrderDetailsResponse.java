package com.acdemic.supermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponse {

    private Long id;

    private ProductResponse products;

    private UserResponse user;

    private Double quantity;

    private Double price;

    private boolean isDeleted;
}
