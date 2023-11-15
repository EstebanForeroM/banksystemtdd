package com.finalproject.frameworks.repositoryLogic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class ClientSerializerTest {

    private static ClientSerializer clientSerializer;

    @BeforeAll
    public static void setUp() {
        clientSerializer = new ClientSerializer();
    }

    @Test
    public void testSerialization() {

        Client client = new Client("123", "Juan", Gender.MALE, "fsaM");
        client.setPhotoPath("path");
        String serializedClient = clientSerializer.serialize(client);

        Client client2 = clientSerializer.deserialize(serializedClient);

        assert client.getId().equals(client2.getId());
        assert client.getName().equals(client2.getName());
        assert client.getGender().equals(client2.getGender());
        assert client.getPassword().equals(client2.getPassword());
        assert client.getGender() == client2.getGender();
        assert client.getPhotoPath().equals(client2.getPhotoPath());

    }
}
