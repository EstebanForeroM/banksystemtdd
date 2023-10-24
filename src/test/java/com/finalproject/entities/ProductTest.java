package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private ProductManager productManager;

    @BeforeEach
    public void setUp() {
        productManager = new ProductManager();
    }

    @Test
    public void addProduct() {
        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);

        assertTrue(productManager.getProducts().contains(Product.AMERICAN_EXPRESS_CARD));

        assertFalse(productManager.getProducts().contains(Product.CHECKING_ACCOUNT));
    }
}
