package com.example.aurum.demo.controller;

import com.example.aurum.demo.dto.AuthRequest;
import com.example.aurum.demo.dto.AuthResponse;
import com.example.aurum.demo.service.AuthService;
import com.example.aurum.demo.repository.UserRepository;
import com.example.aurum.demo.enitity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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

    // ✅ Email/Password Login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    // ✅ Google Sign In (existing users only)
    @GetMapping("/oauth-success")
    public ResponseEntity<?> googleLogin(@AuthenticationPrincipal OAuth2User user) {

        if (user == null) {
            return ResponseEntity.status(401).body("OAuth2 login failed");
        }

        String email = user.getAttribute("email");

        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.status(403)
                    .body("Account not found. Please signup manually first.");
        }

        User existing = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(
                new AuthResponse(existing.getId(), "Google Login Successful"));
    }
}