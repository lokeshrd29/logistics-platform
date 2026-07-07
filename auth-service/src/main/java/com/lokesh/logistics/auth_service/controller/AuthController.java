package com.lokesh.logistics.auth_service.controller;

import com.lokesh.logistics.auth_service.dto.RegisterRequest;
import com.lokesh.logistics.auth_service.dto.RegisterResponse;
import com.lokesh.logistics.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService ;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

}
