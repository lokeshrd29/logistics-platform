package com.lokesh.logistics.auth_service.service.impl;

import com.lokesh.logistics.auth_service.common.enums.AuthProvider;
import com.lokesh.logistics.auth_service.dto.RegisterRequest;
import com.lokesh.logistics.auth_service.dto.RegisterResponse;
import com.lokesh.logistics.auth_service.entity.Role;
import com.lokesh.logistics.auth_service.entity.User;
import com.lokesh.logistics.auth_service.exception.EmailAlreadyExistsException;
import com.lokesh.logistics.auth_service.exception.PhoneAlreadyExistsException;
import com.lokesh.logistics.auth_service.exception.UsernameAlreadyExistsException;
import com.lokesh.logistics.auth_service.repository.RoleRepository;
import com.lokesh.logistics.auth_service.repository.UserRepository;
import com.lokesh.logistics.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername((request.getUserName()))){
            throw new UsernameAlreadyExistsException(request.getUserName());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new PhoneAlreadyExistsException(request.getPhone());
        }

        Role customerRole = roleRepository.findByRole("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .username(request.getUserName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(AuthProvider.LOCAL)
                .roles(Set.of(customerRole))
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .userName(savedUser.getUsername())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .message("User registered successfully")
                .build();
    }
}
