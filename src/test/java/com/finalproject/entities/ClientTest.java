package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Products.Card;
import com.finalproject.entities.Products.Visa;

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

        assertThrows(IllegalArgumentException.class, () -> {
            new Client("Esteban", -1542, Gender.MALE);
        }, "Id cannot be negative.");
    }

    @Test
    public void testClientName() {
        client.setName("Esteban");
        assertEquals("Esteban", client.getName());

        assertDoesNotThrow(() -> client.setName("Esteban"));

        assertThrows(IllegalArgumentException.class, () -> client.setName(null), "Name cannot be null.");

        assertThrows(IllegalArgumentException.class, () -> client.setName("Esteban,"), "Name cannot contain ,.");
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
        ClientProductManager productManager = client.getProductManager();

        assertDoesNotThrow(() -> client.getProductManager());

        assertNotEquals(null, productManager);

        // productManager.addProduct(new Card("123456789", "01/01/2020", new Visa(),
        // 1000.0f));

        // assert productManager == client.getProductManager();
    }

    @Test
    public void getUserProfilePhotoPath() {
        assertEquals("src\\lib\\img\\default.png", client.getProfilePhotoPath());

        assertThrows(FileNotFoundException.class, () -> {
            client.setProfilePhotoPath("This is a path//");
        });

        assertEquals("src\\lib\\img\\default.png", client.getProfilePhotoPath());

        assertDoesNotThrow(() -> {
            client.setProfilePhotoPath("src\\data\\img\\default.png");
        });
    }
}
