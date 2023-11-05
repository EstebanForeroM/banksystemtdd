package com.finalproject.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Product;
import com.finalproject.entities.Products.Card;
import com.finalproject.entities.Products.Visa;
import com.finalproject.frameworks.TextFileProductRepository;

public class ProductRepositoryTest {

    static ProductRepository productRepository;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        productRepository = new TextFileProductRepository("src\\data\\products");
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        productRepository.clear();
    }

    @BeforeEach
    public void setUp() {
        productRepository.clear();
    }

    @Test
    public void testSaveProduct() {
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.saveProduct(null);
        }, "Product cannot be null.");

        Date date = Date.from(new Date().toInstant());

        productRepository.saveProduct(new Card(12345678, date, new Visa(), 1000.0f, 1014));

        List<Product> products = productRepository.getAllProducts();

        assert (products.size() == 1);

        assert (products.get(0).getProductId() == 12345678);

        assert (products.get(0).getProductName().equals("Visa Card"));

        assert (products.get(0).getClientId() == 1014);

        assert (products.get(0).getOpeningDate().equals(date));

        productRepository.saveProduct(new Card(12345678, date, new Visa(), 1000.0f, 1010));

        products = productRepository.getAllProducts();

        assert (products.size() == 1);

        assert (products.get(0).getProductId() == 12345678);
    }

    @Test
    public void testDeleteProduct() {
        Date date = Date.from(new Date().toInstant());
        productRepository.saveProduct(new Card(12345678, date, new Visa(), 1000.0f, 1014));

        // Ensure the product is saved
        assertEquals(1, productRepository.getAllProducts().size());

        // Delete the product
        productRepository.deleteProduct(12345678);

        // Verify product is deleted
        assertTrue(productRepository.getAllProducts().isEmpty());
    }

    @Test
    public void testGetAllProductsAfterDeletion() {
        Date date = Date.from(new Date().toInstant());
        productRepository.saveProduct(new Card(12345678, date, new Visa(), 1000.0f, 1014));
        productRepository.saveProduct(new Card(87654321, date, new Visa(), 2000.0f, 1015));

        // Delete one product
        productRepository.deleteProduct(12345678);

        // Get all products and verify only one remains
        List<Product> products = productRepository.getAllProducts();
        assertEquals(1, products.size());
        assertEquals(87654321, products.get(0).getProductId());
    }

    @Test
    public void testDeleteNonExistentProduct() {
        // Attempt to delete a product that doesn't exist
        productRepository.deleteProduct(99999999);

        // Verify that no exception is thrown and method completes gracefully
    }
}
