package com.finalproject.controllers;

import java.util.List;

import com.finalproject.entities.Product;

public interface ProductRepository {

    void clear();

    void saveProduct(Product product);

    void deleteProduct(int id);

    List<Product> getAllProducts();
}
