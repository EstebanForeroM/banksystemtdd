package com.finalproject.controllers;

import java.io.FileNotFoundException;

import com.finalproject.entities.Client;
import com.finalproject.useCases.ClientManager;

public class AppController {

    private final ClientRepositoryTest clientRepository;

    private final ClientManager clientController;

    public AppController(ClientRepositoryTest clientRepository) {
        this.clientRepository = clientRepository;
        clientController = new ClientManager(clientRepository.getAllClients());
    }

    public void addClient(Client client) throws FileNotFoundException {
        clientController.addClient(client);
        clientRepository.saveClient(client);
    }

    public void deleteClient(int id) {
        clientController.removeClient(id);
        clientRepository.deleteClient(id);
    }

    public void updateClient(Client client) throws FileNotFoundException {
        clientController.updateClient(client);
    }

    public Client getClientById(int id) {
        return clientController.getClientById(id);
    }

    public void getAllClients() {
        clientController.getClients();
    }

    public void getClientByName(String name) {
        clientController.getClientByName(name);
    }

}
