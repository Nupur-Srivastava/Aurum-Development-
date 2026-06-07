package com.example.aurum.demo.enumns;

import lombok.Getter;

@Getter
public enum ProjectSizeUnit {
    LAKH("Lakh"),
    CRORE("Crore"),
    THOUSAND("Thousand"),
    MILLION("Million"),
    BILLION("Billion");

    private final String displayName;

    ProjectSizeUnit(String displayName) {
        this.displayName = displayName;
    }

}
