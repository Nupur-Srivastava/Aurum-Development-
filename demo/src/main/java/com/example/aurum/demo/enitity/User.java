package com.example.aurum.demo.enitity;

import com.example.aurum.demo.enumns.ProjectSizeUnit;
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

    public User() {
    }

    public User(String fullName, String email, String phone, String password, String category, String provider, Boolean verified, Date createdAt, Integer yearsOfExperience,
                String subCategory, Integer projectSize, ProjectSizeUnit projectSizeUnit) {

        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.category = category;
        this.provider = provider;
        this.verified = verified;
        this.yearsOfExperience = yearsOfExperience;
        this.subCategory = subCategory;
        this.projectSize = projectSize;
        this.projectSizeUnit = projectSizeUnit;
        this.createdAt = createdAt;

    }

}