package com.acdemic.supermarket.dto.response;

import com.acdemic.supermarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponse {

    private String name;

    private String number;

    private Double balance;

    private Instant expired_data;

    private String status;

    private UserResponse user;

    private Instant created_at;

}
