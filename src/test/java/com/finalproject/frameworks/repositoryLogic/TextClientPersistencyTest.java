package com.finalproject.frameworks.repositoryLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class TextClientPersistencyTest {

    TextClientPersistency textClientPersistency;

    @BeforeEach
    public void setUp() {
        textClientPersistency = new TextClientPersistency("src\\data\\users");
    }

    @Test
    public void testClientSaving() {
        Client client = new Client("234124", "Esteban", Gender.MALE, "123342");

        textClientPersistency.saveClient(client);

        textClientPersistency.deleteClient("234124");

        assert textClientPersistency.getClient("234124") == null;

        textClientPersistency.saveClient(client);

        client.setName("Juan");

        textClientPersistency.updateClient("234124", client);

        assert textClientPersistency.getClient("234124").getName().equals("Juan");

        textClientPersistency.deleteClient("234124");
    }
}
