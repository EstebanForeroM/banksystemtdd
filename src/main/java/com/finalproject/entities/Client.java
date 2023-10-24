package com.finalproject.entities;

import java.util.List;

public class Client {

    private final int id;
    private String name;
    private Gender gender;
    private List<Product> products;

    public Client(String name, int id, Gender gender, List<Product> products) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
