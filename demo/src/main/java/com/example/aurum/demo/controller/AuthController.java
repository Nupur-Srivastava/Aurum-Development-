package com.example.aurum.demo.controller;

import com.example.aurum.demo.dto.AuthRequest;
import com.example.aurum.demo.dto.AuthResponse;
import com.example.aurum.demo.service.AuthService;
import com.example.aurum.demo.repository.UserRepository;
import com.example.aurum.demo.enitity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody AuthRequest request) {
        return authService.signup(request);
    }

    @GetMapping("/oauth-success")
    public String googleLogin(@AuthenticationPrincipal OAuth2User user) {
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        if (!userRepository.existsByEmail(email)) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFullName(name);
            newUser.setProvider("GOOGLE");
            newUser.setVerified(true);
            newUser.setCreatedAt(new Date());
            userRepository.save(newUser);
        }
        return "Login success: " + email;
    }
}