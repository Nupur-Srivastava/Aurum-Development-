package com.example.aurum.demo.dto;

import com.example.aurum.demo.enumns.ProjectSizeUnit;
import lombok.Data;

import java.util.Date;

@Data
public class AuthRequest {

    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String category;
    private String provider;
    private Boolean verified;
    private Integer yearsOfExperience;
    private String subCategory;
    private Integer projectSize;
    private Date createdAt;
    private ProjectSizeUnit projectSizeUnit;
}
