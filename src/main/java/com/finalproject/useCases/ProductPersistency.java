package com.finalproject.useCases;

import com.finalproject.entities.Product;

public interface ProductPersistency {

    void saveProduct(Product product);

    Product getProduct(String id);

    void deleteProduct(String id);

    void updateProduct(String id, Product product);
}
