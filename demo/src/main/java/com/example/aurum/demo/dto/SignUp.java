package com.example.aurum.demo.dto;

import lombok.Data;

@Data
public class SignUp {
        private String fullName;
        private String email;
        private String phone;
        private String password;
        private String category;
        private Integer yearsOfExperience;
        private String subCategory;
        private Integer projectSize;
        private String projectSizeUnit;
}
