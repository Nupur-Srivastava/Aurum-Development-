package com.example.aurum.demo.enitity;

import com.example.aurum.demo.enumns.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String provider;
    private Boolean verified;
    private Integer yearsOfExperience;
    private Integer projectSize;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private ConsultantSubCat1 subCategory;
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    private Date createdAt;
    private ProjectSizeUnit projectSizeUnit;

    public User() {
    }

    public User(String fullName, String email, String phoneNumber, String password, Role category, String provider, Boolean verified, Date createdAt, Integer yearsOfExperience,
                ConsultantSubCat1 subCategory, Integer projectSize, ProjectSizeUnit projectSizeUnit, ProjectType projectType) {

        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = category;
        this.provider = provider;
        this.verified = verified;
        this.yearsOfExperience = yearsOfExperience;
        this.subCategory = subCategory;
        this.projectSize = projectSize;
        this.projectSizeUnit = projectSizeUnit;
        this.projectType = projectType;
        this.createdAt = createdAt;

    }

}