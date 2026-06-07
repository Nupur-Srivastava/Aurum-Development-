package com.example.aurum.demo.service;

import com.example.aurum.demo.dto.AuthRequest;
import com.example.aurum.demo.dto.AuthResponse;
import com.example.aurum.demo.enitity.User;
import com.example.aurum.demo.enumns.ProjectSizeUnit;
import com.example.aurum.demo.repository.UserRepository;
import com.example.aurum.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse signup(AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered. Please login instead.");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        user.setProvider("LOCAL");
        user.setSubCategory(request.getSubCategory());
        user.setProjectSize(request.getProjectSize());
        user.setProjectType(request.getProjectType());
        user.setProjectSizeUnit(request.getProjectSizeUnit(ProjectSizeUnit.values()));
        user.setCreatedAt(new Date());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setYearsOfExperience(request.getYearsOfExperience());

        user = userRepository.save(user);
        return new AuthResponse(user.getId(), "Signup Successful",null,user.getEmail(),user.getPassword());
    }
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(user.getId(), "Login Successful", token,user.getEmail(),user.getPassword());
    }
}