package com.example.aurum.demo.enumns;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ProjectSizeUnit {
    LAKH("Lakh"),
    CRORE("Crore"),
    THOUSAND("Thousand"),
    MILLION("Million"),
    BILLION("Billion");

    private final String value;

    ProjectSizeUnit(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ProjectSizeUnit fromValue(String value) {

        for (ProjectSizeUnit unit : values()) {
            if (unit.value.equalsIgnoreCase(value)) {
                return unit;
            }
        }

        throw new IllegalArgumentException(
                "Unknown ProjectSizeUnit: " + value
        );
    }
}
