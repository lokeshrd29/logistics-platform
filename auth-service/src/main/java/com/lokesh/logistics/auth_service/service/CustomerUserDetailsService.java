package com.lokesh.logistics.auth_service.service;

import com.lokesh.logistics.auth_service.exception.UserNotFoundException;
import com.lokesh.logistics.auth_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UserNotFoundException {

        return userRepository.getUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));
    }
}
