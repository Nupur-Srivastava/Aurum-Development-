package com.example.aurum.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private Long userId;
    private String message;
    private String email;
    private String token;
}
