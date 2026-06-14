package com.example.aurum.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CategoryHierarchyDTO {

    private String subCategory1;
    private List<SubCategory2SelectionDTO> subCategory2Selections;
}
