package com.lokesh.logistics.auth_service.service;

import com.lokesh.logistics.auth_service.dto.RegisterRequest;
import com.lokesh.logistics.auth_service.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}
