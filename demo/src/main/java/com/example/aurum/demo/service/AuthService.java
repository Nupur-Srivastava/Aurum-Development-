package com.example.aurum.demo.service;

import com.example.aurum.demo.dto.AuthRequest;
import com.example.aurum.demo.dto.AuthResponse;
import com.example.aurum.demo.enitity.User;
import com.example.aurum.demo.repository.UserRepository;
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

    // In-memory OTP store (fine for dev; use Redis in prod on GCP)
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
        user.setCreatedAt(new Date());

        user = userRepository.save(user);
        return new AuthResponse(user.getId(), "Signup Successful");
    }

    //    public void sendOtp(String phoneNumber) {
//        String otp = String.valueOf(
//                ThreadLocalRandom.current().nextInt(100000, 999999)
//        );
//        otpStore.put(phoneNumber, otp);
//
//        System.out.println("OTP for " + phoneNumber + " : " + otp); // replace with real SMS
//    }
//
//    public AuthResponse verifyOtp(String phoneNumber, String otp) {
//        String savedOtp = otpStore.get(phoneNumber);
//
//        if (savedOtp == null || !savedOtp.equals(otp)) {
//            throw new RuntimeException("Invalid OTP");
//        }
//
//        otpStore.remove(phoneNumber);
//
//        if (userRepository.existsByPhone(phoneNumber)) {
//            User user = userRepository.findByPhone(phoneNumber)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//            return new AuthResponse(user.getId(), "Login Successful");
//        }
//
//        return new AuthResponse(null, "OTP verified. Please complete signup.");
//    }
//    public AuthResponse login(LoginRequest request) {
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() ->
//                        new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(
//                request.getPassword(),
//                user.getPassword())) {
//
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        return new AuthResponse(
//                user.getId(),
//                "Login Successful");
//    }
}