package com.finalproject.entities;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {

    private int id;
    private String name;
    private Gender gender;
    private final ClientProductManager productManager;
    private final String password;
    private String profilePhotoPath;

    public Client(String name, int id, Gender gender, String password) {
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be negative.");
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.productManager = new ClientProductManager();
        profilePhotoPath = "src\\lib\\img\\default.png";
        this.password = password;
        validations();
    }

    public Client(String name, int id, Gender gender, ClientProductManager productManager, String password) {
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be negative.");
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.productManager = productManager;
        profilePhotoPath = "src\\lib\\img\\default.png";
        this.password = password;
        validations();
    }

    public String getPassword() {
        return password;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null.");
        if (name.contains(","))
            throw new IllegalArgumentException("Name cannot contain ,.");

        this.name = name;
    }

    public int setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be negative.");

        return this.id = id;
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

    public ClientProductManager getProductManager() {
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

    private void validations() {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null.");
        if (name.contains(","))
            throw new IllegalArgumentException("Name cannot contain ,.");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be negative.");
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null.");
        if (password.contains(","))
            throw new IllegalArgumentException("Password cannot contain ,.");
        if (password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty.");

    }
}