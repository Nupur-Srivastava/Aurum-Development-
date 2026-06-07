package com.example.aurum.demo.dto;

import com.example.aurum.demo.enumns.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AuthRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private String provider;
    private Boolean verified;
    private Integer yearsOfExperience;
    private ConsultantSubCat1 subCategory;
    private ProjectType projectType;
    private Integer projectSize;
    private Date createdAt;
    private ProjectSizeUnit projectSizeUnit;
    private List<String> categoryHierarchy;

    public ProjectSizeUnit getProjectSizeUnit(ProjectSizeUnit[] values) {
        return null;
    }
}
