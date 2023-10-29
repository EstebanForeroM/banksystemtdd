package com.finalproject.useCases;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class ClientControllerTest {

    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        List<Client> clients = new ArrayList<Client>();
        clientController = new ClientController(clients);
    }

    @Test
    public void addClient() throws FileNotFoundException {
        Client client = new Client("Joe", 1014, Gender.MALE);
        clientController.addClient(client);
        clientEqual(client, clientController.getClientById(1014));

        assertThrows(IllegalArgumentException.class, () -> {
            clientController.addClient(new Client("Esteban", 1014, Gender.MALE));
        }, "Client already exists");

        client = new Client("Joa", 1012, Gender.FEMALE);

        assertNotEquals(client.getName(), clientController.getClientById(1014).getName());

        clientController.addClient(client);

        assertEquals(clientController.getClients().size(), 2);
    }

    @Test
    public void getClientByName() {
        clientController.addClient(new Client("E", 1, Gender.MALE));
        clientController.addClient(new Client("J", 2, Gender.FEMALE));
        clientController.addClient(new Client("TestClient", 3, Gender.OTHER));

        Client client = clientController.getClientByName("TestClient");
        assert client.getName().equals("TestClient");

        client.setName("TestClient2");

        assertNotEquals(client.getName(), clientController.getClientByName("TestClient").getName());
    }

    @Test
    public void getClients() {
        clientController.addClient(new Client("E", 1, Gender.MALE));
        List<Client> clients = clientController.getClients();

        clients.get(0).setName("Esteban");

        assertNotEquals(clients.get(0).getName(), clientController.getClientById(1).getName());
    }

    @Test
    public void deleteClient() {
        clientController.addClient(new Client("E", 1, Gender.MALE));

        clientController.addClient(new Client("AAAA", 123, Gender.MALE));

        assertDoesNotThrow(() -> {
            clientController.getClientById(1);
        }, "Client does not exist");

        clientController.removeClient(1);

        assertThrows(IllegalArgumentException.class, () -> {
            clientController.getClientById(1);
        }, "Client does not exist");

        assertThrows(IllegalArgumentException.class, () -> {
            clientController.removeClient(1);
        }, "Client does not exist");

    }

    @Test
    public void getClientById() {
        clientController.addClient(new Client("LLL", 14545, Gender.OTHER));

        Client client = clientController.getClientById(14545);

        clientEqual(client, clientController.getClientById(14545));
    }

    private void clientEqual(Client client1, Client client2) {
        assertTrue(client1.getName().equals(client2.getName()));
        assertTrue(client1.getId() == client2.getId());
        assertTrue(client1.getGender() == client2.getGender());
    }
}
