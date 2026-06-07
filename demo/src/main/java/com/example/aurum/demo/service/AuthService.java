package com.example.aurum.demo.service;

import com.example.aurum.demo.dto.AuthRequest;
import com.example.aurum.demo.dto.AuthResponse;
import com.example.aurum.demo.enitity.User;
import com.example.aurum.demo.repository.UserRepository;
import com.example.aurum.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    public AuthResponse signup(AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setCategory(request.getCategory());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        user.setProvider("LOCAL");
        user.setYearsOfExperience(request.getYearsOfExperience());
        user.setSubCategory(request.getSubCategory());
        user.setProjectSize(request.getProjectSize());
        user.setProjectSizeUnit(request.getProjectSizeUnit());
        user.setCreatedAt(new Date());

        user = userRepository.save(user);
        return new AuthResponse(user.getId(), "Signup Successful",null);
    }
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(user.getId(), "Login Successful", token);
    }
}