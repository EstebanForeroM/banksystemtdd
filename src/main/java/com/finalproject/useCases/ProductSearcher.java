package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Product;
import com.finalproject.entities.products.ProductType;

public class ProductSearcher {
    ProductRepository productRepository;

    Set<Product> products;

    public ProductSearcher(ProductRepository productRepository) {
        this.productRepository = productRepository;
        productRepository.setChangeListener(this::onRepositoryChange);
    }

    private void onRepositoryChange() {
        reloadDTO();
    }

    private void reloadDTO() {
        products = productRepository.getProducts();
    }

    public Set<Product> getProductsById(String id) {
        Set<Product> productsById = new HashSet<>();

        for (Product product : products) {
            if (product.getId().contains(id))
                productsById.add(product);
        }

        return productsById;
    }

    public Set<Product> getProductsByOwner(String ownerId) {
        Set<Product> productsByOwner = new HashSet<>();

        for (Product product : products) {
            if (product.getOwnerId().contains(ownerId))
                productsByOwner.add(product);
        }

        return productsByOwner;
    }

    public Set<Product> getProductsByType(ProductType productType) {
        Set<Product> productsByType = new HashSet<>();

        for (Product product : products) {
            if (product.getProductName().contains(productType.getName()))
                productsByType.add(product);
        }

        return productsByType;
    }
}
