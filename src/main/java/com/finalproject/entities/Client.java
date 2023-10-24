package com.finalproject.entities;

public class Client {

    private final int id;
    private String name;
    private Gender gender;
    private final ProductManager productManager;

    public Client(String name, int id, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.productManager = new ProductManager();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ProductManager getProductManager() {
        return productManager;
    }
}