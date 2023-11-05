package com.finalproject.entities;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    public static Gender fromString(String genderString) {
        for (Gender gender : Gender.values()) {
            if (gender.getGenderName().equalsIgnoreCase(genderString)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No constant with text " + genderString + " found");
    }
}