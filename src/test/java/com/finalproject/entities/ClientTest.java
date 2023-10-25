package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client("Esteban", 1014, Gender.MALE);
    }

    @Test
    public void testClientCreation() {
        assertDoesNotThrow(() -> new Client("Esteban", 1014, Gender.MALE));
        assertEquals("Esteban", client.getName());
        assertEquals(1014, client.getId());
        assertEquals(Gender.MALE, client.getGender());
    }

    @Test
    public void testClientName() {
        client.setName("Esteban");
        assertEquals("Esteban", client.getName());

        assertDoesNotThrow(() -> client.setName("Esteban"));

        assertThrows(IllegalArgumentException.class, () -> client.setName(null), "Name cannot be null.");
    }

    @Test
    public void testClientGender() {
        client.setGender(Gender.FEMALE);

        assertEquals(client.getGender(), Gender.FEMALE);

        assertThrows(IllegalArgumentException.class, () -> {
            client.setGender(null);
        }, "Gender cannot be null.");
    }

    @Test
    public void productManager() {
        ProductManager productManager = client.getProductManager();

        assertDoesNotThrow(() -> client.getProductManager());

        assertNotEquals(null, productManager);

        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);

        assert productManager == client.getProductManager();
    }

    @Test
    public void getUserProfilePhotoPath() {
        assertEquals("src\\lib\\img\\default.png", client.getProfilePhotoPath());
    }
}
