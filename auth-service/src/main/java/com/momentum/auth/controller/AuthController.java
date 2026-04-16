package com.momentum.auth.controller;

import com.momentum.auth.dto.AuthResponse;
import com.momentum.auth.dto.LoginRequest;
import com.momentum.auth.dto.RegisterRequest;
import com.momentum.auth.dto.UserResponse;
import com.momentum.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
       return authService.login(loginRequest);
    }

    @GetMapping("/me")
    public UserResponse getProfile(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return authService.getProfile(userId);
    }
}
