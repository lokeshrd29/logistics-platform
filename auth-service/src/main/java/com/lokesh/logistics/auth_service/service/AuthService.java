package com.lokesh.logistics.auth_service.service;

import com.lokesh.logistics.auth_service.dto.LoginRequest;
import com.lokesh.logistics.auth_service.dto.LoginResponse;
import com.lokesh.logistics.auth_service.dto.RegisterRequest;
import com.lokesh.logistics.auth_service.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest login);

}
