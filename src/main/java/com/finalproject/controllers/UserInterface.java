package com.finalproject.controllers;

import java.util.List;

import com.finalproject.entities.Client;

public interface UserInterface {

    void displayClient(Client client);

    void displayAllClients(List<Client> clients);
}
