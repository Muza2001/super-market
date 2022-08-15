package com.acdemic.supermarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardRequest {

    private String name;

    private String number;

    private Double balance;

    private Instant expired_data;

    private String status;

    private Long userId;

}
