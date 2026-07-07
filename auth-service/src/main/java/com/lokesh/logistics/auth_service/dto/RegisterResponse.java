package com.lokesh.logistics.auth_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String userName;

    private String email;

    private String phone;

    private String message;
}