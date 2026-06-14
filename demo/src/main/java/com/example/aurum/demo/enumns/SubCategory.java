package com.example.aurum.demo.enumns;

public enum SubCategory {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    DEVOPS("DevOps"),
    MOBILE("Mobile");

    private final String displayName;

    SubCategory(String displayName) {
        this.displayName = displayName;
    }
}
