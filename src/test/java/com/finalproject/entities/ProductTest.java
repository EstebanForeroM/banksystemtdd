package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

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

        assertTrue(productManager.hasProduct(Product.AMERICAN_EXPRESS_CARD));

        assertFalse(productManager.hasProduct(Product.CHECKING_ACCOUNT));

        assertThrows(IllegalArgumentException.class, () -> {
            productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);
        }, "Product already exists in the system.");

        assertThrows(IllegalArgumentException.class, () -> {
            productManager.addProduct(null);
        });
    }

    @Test
    public void deleteProduct() {
        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);

        assertTrue(productManager.hasProduct(Product.AMERICAN_EXPRESS_CARD));

        productManager.removeProduct(Product.AMERICAN_EXPRESS_CARD);

        assertFalse(productManager.hasProduct(Product.AMERICAN_EXPRESS_CARD));

        assertThrows(IllegalArgumentException.class, () -> {
            productManager.removeProduct(Product.AMERICAN_EXPRESS_CARD);
        }, "Product does not exist in the system.");

        assertThrows(IllegalArgumentException.class, () -> {
            productManager.removeProduct(null);
        }, "Product cannot be null.");
    }

    @Test
    public void getProducts() {
        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);
        productManager.addProduct(Product.CHECKING_ACCOUNT);
        productManager.addProduct(Product.SAVINGS_ACCOUNT);

        Iterator<Product> iterator = productManager.getProductsIterator();

        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            assertTrue(productManager.hasProduct(product));
        }
    }
}
