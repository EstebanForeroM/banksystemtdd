package com.finalproject.controllers;

import java.util.List;

import com.finalproject.entities.Client;

public interface ClientRepository {

    void clear();

    void saveClient(Client client);

    void deleteClient(int id);

    List<Client> getAllClients();
}
