package com.finalproject.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.finalproject.entities.Product;
import com.finalproject.entities.Products.ProductsTypes;

public class ProductManager {
    private List<Product> products;

    private Set<Integer> productIds;

    public ProductManager(List<Product> products) {
        this.products = products;
        for (Product product : products) {
            if (productIds.contains(product.getProductId()))
                throw new IllegalArgumentException("Product already exists in the system.");
            productIds.add(product.getProductId());
        }
    }

    ProductManager() {
        products = new ArrayList<Product>();
    }

    public List<Product> geProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");
        if (products.contains(product))
            throw new IllegalArgumentException("Product already exists in the system.");
        if (productIds.contains(product.getProductId()))
            throw new IllegalArgumentException("Product already exists in the system.");

        products.add(product);
    }

    public boolean existKey(int id) {
        return productIds.contains(id);
    }

    public void removeProduct(int id) {
        if (getProductByID(id) == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if (products.contains(getProductByID(id)) == false)
            throw new IllegalArgumentException("Product does not exist in the system.");

        Product product = getProductByID(id);

        product.deleteClient();

        products.remove(product);
    }

    public Product getProductByID(int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product does not exist");
    }

    public List<Product> getProductsByClientID(int id) {
        List<Product> clientProducts = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getClientId() == id) {
                clientProducts.add(product);
            }
        }
        return clientProducts;
    }

    public List<Product> getProductsByType(ProductsTypes type) {
        List<Product> clientProducts = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getProductName().equals(type.getType())) {
                clientProducts.add(product);
            }
        }
        return clientProducts;
    }
}
