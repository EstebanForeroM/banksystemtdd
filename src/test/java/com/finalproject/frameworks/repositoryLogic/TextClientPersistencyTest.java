package com.finalproject.frameworks.repositoryLogic;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class TextClientPersistencyTest {

    TextClientPersistency textClientPersistency;

    @BeforeEach
    public void setUp() {
        textClientPersistency = new TextClientPersistency("src\\data\\users", new ClientSerializer());
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

    @Test
    public void clientList() {
        textClientPersistency.saveClient(new Client("4234", "estean", Gender.MALE, "12t3"));

        textClientPersistency.saveClient(new Client("32432", "Juan", Gender.MALE, "4234"));

        textClientPersistency.saveClient(new Client("2345", "fdsaf", Gender.OTHER, "4dfg435"));

        Set<Client> clients = textClientPersistency.getClients();

        assert clients.size() == 3;

        Iterator<Client> clientIterator = clients.iterator();

        while (clientIterator.hasNext()) {
            Client client = clientIterator.next();

            assert client.getId().equals("4234") || client.getId().equals("32432") || client.getId().equals("2345");
        }
    }
}
