package com.finalproject.useCases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.entities.Client;

public class ClientManager {
    private List<Client> clients;

    public ClientManager(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (containId(client.getId())) {
            throw new IllegalArgumentException("Client already exists");
        }

        Client newClient = cloneClient(client);

        clients.add(newClient);
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

    public void updateClient(Client client) throws FileNotFoundException {
        Client oldClient = getClientById(client.getId());

        oldClient.setName(client.getName());
        oldClient.setGender(client.getGender());
        oldClient.setProfilePhotoPath(client.getProfilePhotoPath());
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
            copiedList.add(client);
        }

        return copiedList;
    }

    public Client getClientByName(String name) {
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                return client;
            }
        }

        throw new IllegalArgumentException("Client does not exist");
    }

    private Client cloneClient(Client client) {
        Client newClient = new Client(client.getName(), client.getId(), client.getGender(), client.getPassword());
        try {
            newClient.setProfilePhotoPath(client.getProfilePhotoPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newClient;
    }
}
