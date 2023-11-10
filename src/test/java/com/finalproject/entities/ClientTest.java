package com.finalproject.entities;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void createClient() {
        Client client = new Client("1014739191", "Esteban", Gender.MALE, "3213");

        assertThrows(NullPointerException.class, () -> {
            new Client(null, "Esteban", Gender.MALE, "11312");
        });

        assertThrows(NullPointerException.class, () -> {
            new Client("1014739191", null, Gender.MALE, "pasawe");
        });

        assertThrows(NullPointerException.class, () -> {
            new Client("1014739191", "Esteban", null, "namina");
        });

        assert client.getId().equals("1014739191");
        assert client.getName().equals("Esteban");
        assert client.getGender() == Gender.MALE;
        assert client.getPassword().equals("3213");
    }

    @Test
    public void changeClientName() {
        Client client = new Client("1014739191", "Esteban", Gender.MALE, "pasisis");

        assertThrows(NullPointerException.class, () -> {
            client.setName(null);
        });

        client.setName("Juan");

        assert client.getName().equals("Juan");

        assertThrows(IllegalArgumentException.class, () -> {
            client.setName("");
        }, "Name must not be empty");

        assertThrows(IllegalArgumentException.class, () -> {
            client.setName(" ");
        }, "Name must not be empty");

        assertThrows(IllegalArgumentException.class, () -> {
            client.setName("sf,");
        }, "Name must not contain ,");

        assert client.getName().equals("Juan");
    }

    @Test
    public void changeClientGender() {
        Client client = new Client("1014739191", "Esteban", Gender.MALE, "fsdfsdf");

        assertThrows(NullPointerException.class, () -> {
            client.setGender(null);
        });

        client.setGender(Gender.FEMALE);

        assert client.getGender() == Gender.FEMALE;
    }
}
