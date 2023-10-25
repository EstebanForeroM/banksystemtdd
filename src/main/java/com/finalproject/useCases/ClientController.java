package com.finalproject.useCases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.entities.Client;

public class ClientController {
    private List<Client> clients;

    public ClientController(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (containId(client.getId())) {
            throw new IllegalArgumentException("Client already exists");
        }

        clients.add(cloneClient(client));
    }

    public void removeClient(int id) {
        clients.remove(getClientById(id));
    }

    private boolean containId(int id) {
        for (Client client : clients) {
            return client.getId() == id;
        }
        return false;
    }

    public Client getClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }

        throw new IllegalArgumentException("Client does not exist");
    }

    public List<Client> getClients() {
        List<Client> copiedList = new ArrayList<>();

        for (Client client : clients) {
            copiedList.add(cloneClient(client));
        }

        return copiedList;
    }

    private Client cloneClient(Client client) {
        Client newClient = new Client(client.getName(), client.getId(), client.getGender());
        try {
            newClient.setProfilePhotoPath(client.getProfilePhotoPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newClient;
    }
}
