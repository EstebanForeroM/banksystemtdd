package com.finalproject.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ClientProductManager {

    private Set<Product> products;

    public ClientProductManager() {
        this.products = new HashSet<>();
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");
        if (products.contains(product))
            throw new IllegalArgumentException("Product already exists in the system.");

        product.OnDeleteClient(() -> {
            products.remove(product);
        });
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if (products.contains(product) == false)
            throw new IllegalArgumentException("Product does not exist in the system.");

        products.remove(product);
    }

    public void removeProduct(int productId) {

        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == productId) {
                iterator.remove();
                return;
            }
        }

        throw new IllegalArgumentException("Product does not exist in the system.");
    }

    public boolean hasProduct(Product product) {
        return products.contains(product);
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Iterator<Product> getProductsIterator() {
        return products.iterator();
    }

}