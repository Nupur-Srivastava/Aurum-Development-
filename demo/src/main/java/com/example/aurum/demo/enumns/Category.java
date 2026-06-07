package com.example.aurum.demo.enumns;

public enum Category {
    DEVELOPER("Developer"),
    CLIENT("Client"),
    INVESTOR("Investor");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
