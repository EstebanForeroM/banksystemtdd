package com.finalproject.controllers;

import java.util.LinkedList;

import com.finalproject.entities.Client;

public interface ClientRepository {

    void saveClient(Client client);

    void deleteClient(Client client);

    LinkedList<Client> getAllClients();
}
