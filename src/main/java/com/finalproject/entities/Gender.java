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
}