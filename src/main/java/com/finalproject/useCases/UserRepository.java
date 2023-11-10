package com.finalproject.useCases;

import java.util.Set;

import com.finalproject.entities.Client;

public interface UserRepository {

    void saveClient(Client client);

    Client getClient(String id);

    void deleteClient(String id);

    void updateClient(String id, Client client);

    Set<Client> getClients();

    void setChangeListener(Runnable callback);
}
