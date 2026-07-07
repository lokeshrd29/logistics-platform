package com.lokesh.logistics.auth_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private String refreshToken;

    private String tokenType; // Bearer

    private Long expiresIn;   // seconds

    private UserResponse user;
}
