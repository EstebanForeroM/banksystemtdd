package com.finalproject.useCases;

import java.util.Set;

import com.finalproject.entities.Product;

public interface ProductRepository {

    void saveProduct(Product product);

    Product getProduct(String id);

    void deleteProduct(String id);

    void updateProduct(String id, Product product);

    Set<Product> getProducts();

    void setChangeListener(Runnable callback);
}
