package com.example.aurum.demo.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "professional_details")
public class ProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer yearsOfExperience;
    private String subCategory;
    private Integer projectSize;
    private String projectSizeUnit;
}