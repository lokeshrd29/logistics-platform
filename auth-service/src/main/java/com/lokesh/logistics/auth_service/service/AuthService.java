package com.lokesh.logistics.auth_service.service;

import com.lokesh.logistics.auth_service.dto.*;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest login);

    UserResponse getCurrentUser();

    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
