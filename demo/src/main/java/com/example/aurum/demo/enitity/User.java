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

    @Id  // ✅ THIS IS THE FIX
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

    @Enumerated(EnumType.STRING)
    private ProjectSizeUnit projectSizeUnit;

    public User() {}
}
