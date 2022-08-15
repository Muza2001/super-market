package com.acdemic.supermarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstname;

    private String lastname;

    private String username;

    private String phoneNumber;

    private String password;

    private Long cashbackId;

    private Long roleId;

}
