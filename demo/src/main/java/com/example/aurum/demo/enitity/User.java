package com.example.aurum.demo.enitity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String password;
    private String category;
    private String provider;
    private Boolean verified;
    private Date createdAt;
}