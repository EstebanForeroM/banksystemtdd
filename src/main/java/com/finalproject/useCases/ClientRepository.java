package com.finalproject.useCases;

import com.finalproject.entities.Client;

public interface ClientRepository {

    void saveClient(Client client);

    Client getClient(String id);

    void deleteClient(String id);

    void updateClient(String id, Client client);
}
