package com.finalproject.useCases;

import java.util.List;

import com.finalproject.entities.Client;

public class ClientController {
    private List<Client> clients;

    public ClientController(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void deleteClient(Client client) {
        clients.remove(client);
    }
}
