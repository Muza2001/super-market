package com.acdemic.supermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String phoneNumber;

    private String password;

    private String status;

    private CashbackResponse cashback;

    private RoleResponse role;

}
