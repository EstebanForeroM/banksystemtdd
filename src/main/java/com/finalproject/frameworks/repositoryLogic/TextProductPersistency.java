package com.finalproject.frameworks.repositoryLogic;

import java.util.List;
import java.util.Set;

import com.finalproject.entities.Product;
import com.finalproject.useCases.ProductRepository;

public class TextProductPersistency implements ProductRepository {

    private TextPersistency<Product> textPersistency;

    public TextProductPersistency(String filePath, Serializer<Product> serializer) {
        textPersistency = new TextPersistency<>(filePath, "products.txt", serializer);
    }

    @Override
    public void saveProduct(Product product) {
        textPersistency.saveObject(product);
    }

    @Override
    public Product getProduct(String id) {
        return textPersistency.getObject(id);
    }

    @Override
    public void deleteProduct(String id) {
        textPersistency.deleteObject(id);
    }

    @Override
    public void updateProduct(String id, Product product) {
        textPersistency.updateObject(id, product);
    }

    @Override
    public Set<Product> getProducts() {
        return textPersistency.getObjectsList();
    }

    @Override
    public void setChangeListener(Runnable callback) {
        textPersistency.addChangeListener(callback);
    }
}
