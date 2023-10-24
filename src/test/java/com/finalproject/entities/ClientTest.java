package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client("Esteban", 1014, Gender.MALE);
    }

    @Test
    public void testClientCreation() {

        List<Product> products = new ArrayList<>();
        products.add(Product.AMERICAN_EXPRESS_CARD);
        products.add(Product.CHECKING_ACCOUNT);
        products.add(Product.SAVINGS_ACCOUNT);
        assertDoesNotThrow(() -> new Client("Esteban", 1014, Gender.MALE));
    }
}
