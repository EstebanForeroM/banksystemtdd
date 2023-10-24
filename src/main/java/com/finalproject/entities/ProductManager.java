package com.finalproject.entities;

import java.util.HashSet;
import java.util.Set;

public class ProductManager {

    private Set<Product> products;

    public ProductManager() {
        this.products = new HashSet<>();
    }

    public boolean addProduct(Product product) {
        return products.add(product); // Returns false if the product is already present
    }

    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public Set<Product> getProducts() {
        return products;
    }
}