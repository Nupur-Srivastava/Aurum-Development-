package com.example.aurum.demo.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String category;
//    private String otp;
}
