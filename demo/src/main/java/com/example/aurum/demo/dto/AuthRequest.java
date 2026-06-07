package com.example.aurum.demo.dto;

import com.example.aurum.demo.enumns.*;
import lombok.Data;

import java.util.Date;

@Data
public class AuthRequest {

    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Role category;
    private String provider;
    private Boolean verified;
    private Integer yearsOfExperience;
    private ConsultantSubCat1 subCategory;
    private ProjectType projectType;
    private Integer projectSize;
    private Date createdAt;
    private ProjectSizeUnit projectSizeUnit;
}
