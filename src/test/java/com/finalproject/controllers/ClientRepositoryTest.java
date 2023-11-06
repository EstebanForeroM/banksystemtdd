package com.finalproject.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.frameworks.repositoryLogic.TextFileClientRepository;

public class ClientRepositoryTest {

    private static ClientRepository clientRepository;

    @BeforeAll
    public static void setup() {
        clientRepository = new TextFileClientRepository("src\\data\\users\\users.txt");
    }

    @BeforeEach
    public void clear() {
        clientRepository.clear();
    }

    @AfterAll
    public static void tearDown() {
        clientRepository.clear();
    }

    @Test
    public void testSaveClient() {
        assertThrows(IllegalArgumentException.class, () -> {
            clientRepository.saveClient(null);
        }, "Client cannot be null.");

        Client clientToSave = new Client("Esteban", 1014, Gender.MALE, "idk");

        clientRepository.saveClient(clientToSave);

        List<Client> clients = clientRepository.getAllClients();

        assert (clients.size() == 1);

        assert (clients.get(0).getId() == 1014);

        clientRepository.saveClient(new Client("Juan", 1412, Gender.OTHER, "paswer"));

        clients = clientRepository.getAllClients();

        assert (clients.size() == 2);

        assert (clients.get(1).getId() == 1412);

        assert (clients.get(1).getName().equals("Juan"));

        clientRepository.saveClient(new Client("EstebanVersion2", 1014, Gender.MALE, "idkdk"));

        clients = clientRepository.getAllClients();

        assert (clients.size() == 2);

        assert (clients.get(1).getName().equals("EstebanVersion2"));

        assert (clients.get(1).getId() == 1014);
    }

    @Test
    public void testDeleteClient() {
        Client client1 = new Client("Esteban", 1014, Gender.MALE, "thsfdka");
        clientRepository.saveClient(client1);

        // Ensure the client is saved
        Assertions.assertEquals(1, clientRepository.getAllClients().size());

        // Delete the client
        clientRepository.deleteClient(1014);

        // Verify client is deleted
        Assertions.assertTrue(clientRepository.getAllClients().isEmpty());
    }

    @Test
    public void testGetAllClientsAfterDeletion() {
        Client client1 = new Client("Esteban", 1014, Gender.MALE, "243242");
        Client client2 = new Client("Juan", 1412, Gender.OTHER, "dew");
        clientRepository.saveClient(client1);
        clientRepository.saveClient(client2);

        // Delete one client
        clientRepository.deleteClient(1014);

        // Get all clients and verify only one remains
        List<Client> clients = clientRepository.getAllClients();
        Assertions.assertEquals(1, clients.size());
        Assertions.assertEquals(1412, clients.get(0).getId());
    }

    @Test
    public void testDeleteNonExistentClient() {
        // Attempt to delete a client that doesn't exist
        clientRepository.deleteClient(9999);

        // Verify that no exception is thrown and method completes gracefully
    }
}
