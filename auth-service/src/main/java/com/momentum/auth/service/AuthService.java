package com.momentum.auth.service;


import com.momentum.auth.entity.Role;
import com.momentum.auth.dto.AuthResponse;
import com.momentum.auth.dto.LoginRequest;
import com.momentum.auth.dto.RegisterRequest;
import com.momentum.auth.dto.UserResponse;
import com.momentum.auth.entity.User;
import com.momentum.auth.repository.UserRepository;
import com.momentum.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register (RegisterRequest registerRequest) {
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login (LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResponse(jwtService.generateToken(user));
    }

    public UserResponse getProfile (Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getEmail(), user.getCreatedAt());
    }
}
