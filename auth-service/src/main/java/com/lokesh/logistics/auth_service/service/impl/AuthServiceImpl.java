package com.lokesh.logistics.auth_service.service.impl;

import com.lokesh.logistics.auth_service.common.enums.AuthProvider;
import com.lokesh.logistics.auth_service.dto.*;
import com.lokesh.logistics.auth_service.entity.Role;
import com.lokesh.logistics.auth_service.entity.User;
import com.lokesh.logistics.auth_service.exception.*;
import com.lokesh.logistics.auth_service.repository.RoleRepository;
import com.lokesh.logistics.auth_service.repository.UserRepository;
import com.lokesh.logistics.auth_service.security.JwtService;
import com.lokesh.logistics.auth_service.service.AuthService;
import com.lokesh.logistics.auth_service.service.CustomerUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService ;

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

    @Override
    public LoginResponse login(LoginRequest loginRequest)
    {
        User user = userRepository.findUserByUsername(loginRequest.getUserName())
                .orElseThrow(() ->
                        new UserNotFoundException(loginRequest.getUserName()));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        if (!user.isEnabled()) {
            throw new DisabledUserFoundExpection("Account is disabled");
        }

        String accessToken = jwtService.generateAccessToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiration())
                .user(
                        UserResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                                .enabled(user.isEnabled())
                                .emailVerified(user.isEmailVerified())
                                .roles(
                                        user.getRoles()
                                                .stream()
                                                .map(Role::getRole)
                                                .collect(Collectors.toSet())
                                )
                                .build()
                )
                .build();
    }

    @Override
    public UserResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(Role::getRole)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request)
    {
        String refreshToken = request.getRefreshToken();

        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        String newAccessToken =
                jwtService.generateAccessToken(user);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiration())
                .build();
    }
}
