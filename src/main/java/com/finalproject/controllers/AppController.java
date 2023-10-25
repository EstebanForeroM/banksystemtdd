package com.finalproject.controllers;

import com.finalproject.entities.Client;
import com.finalproject.useCases.ClientController;

public class AppController {

    private final UserInterface userInterface;

    private final ClientRepository clientRepository;

    private final ClientController clientController;

    public AppController(UserInterface userInterface, ClientRepository clientRepository) {
        this.userInterface = userInterface;
        this.clientRepository = clientRepository;
        clientController = new ClientController(clientRepository.getAllClients());
    }

    public void addClient(Client client) {
        clientController.addClient(client);
        clientRepository.saveClient(client);
    }

    public void deleteClient(Client client) {
        clientController.deleteClient(client);
        clientRepository.deleteClient(client);
    }
}
