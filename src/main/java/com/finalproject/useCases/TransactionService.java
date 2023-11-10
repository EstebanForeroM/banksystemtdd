package com.finalproject.useCases;

import com.finalproject.entities.Product;
import com.finalproject.entities.products.Transactional;

public class TransactionService {

    ProductRepository productRepository;

    TransactionService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deposit(String id, double amount) {
        Product product = productRepository.getProduct(id);

        if (product instanceof Transactional) {
            Transactional transactional = (Transactional) product;
            transactional.deposit(amount);
            productRepository.updateProduct(id, product);
        } else {
            throw new IllegalArgumentException("Product is not transactional");
        }
    }

    public void withdraw(String id, double amount) {
        Product product = productRepository.getProduct(id);

        if (product instanceof Transactional) {
            Transactional transactional = (Transactional) product;
            transactional.withdraw(amount);
            productRepository.updateProduct(id, product);
        } else {
            throw new IllegalArgumentException("Product is not transactional");
        }
    }
}
