package com.finalproject.entities;

import java.util.Objects;

public class Client implements Identifiable {
    private String id;
    private String name;
    private Gender gender;
    private String password;
    private String photoPath;

    public Client(String id, String name, Gender gender, String password) {
        validations(id, name, gender, password);
        name = name.trim();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    private void validations(String id, String name, Gender gender, String password) {
        notNullValidations(gender);
        idValidations(id);
        passwordValidation(password);
        stringFieldValidations(name);
    }

    private void notNullValidations(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                throw new NullPointerException();
        }
    }

    private void idValidations(String id) {
        notNullValidations(id);
        if (id.isEmpty())
            throw new IllegalArgumentException("Product ID must not be empty");
        comproveOnlyNubers(id);
    }

    private void passwordValidation(String password) {
        notNullValidations(password);
        if (password.length() < 4)
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        stringFieldValidations(password);
    }

    private void stringFieldValidations(String name) {
        if (name.contains(","))
            throw new IllegalArgumentException("Name must not contain commas");

        if (name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty");
    }

    private void comproveOnlyNubers(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new IllegalArgumentException("Product ID must contain only numbers");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        idValidations(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.trim();
        stringFieldValidations(name);
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        notNullValidations(gender);
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        passwordValidation(password);
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client) {
            Client client = (Client) obj;
            return client.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
