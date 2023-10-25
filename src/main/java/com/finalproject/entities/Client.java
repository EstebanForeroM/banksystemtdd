package com.finalproject.entities;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {

    private final int id;
    private String name;
    private Gender gender;
    private final ProductManager productManager;
    private String profilePhotoPath;

    public Client(String name, int id, Gender gender) {
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be negative.");
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.productManager = new ProductManager();
        profilePhotoPath = "src\\lib\\img\\default.png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null.");

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null)
            throw new IllegalArgumentException("Gender cannot be null.");

        this.gender = gender;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(String profilePhotoPath) throws FileNotFoundException {
        if (Files.exists(Paths.get(profilePhotoPath))) {
            this.profilePhotoPath = profilePhotoPath;
        } else {
            throw new FileNotFoundException("The file path " + profilePhotoPath + " does not exist");
        }
    }
}