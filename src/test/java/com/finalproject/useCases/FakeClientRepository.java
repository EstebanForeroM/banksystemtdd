package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Client;

public class FakeClientRepository implements ClientRepository {

    Set<Client> clients;
    Runnable callback;

    FakeClientRepository() {
        clients = new HashSet<>();
    }

    @Override
    public void saveClient(Client client) {
        clients.add(client);
        callback.run();
    }

    @Override
    public Client getClient(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClient'");
    }

    @Override
    public void deleteClient(String id) {
        clients.removeIf(client -> client.getId().equals(id));
        callback.run();
    }

    @Override
    public void updateClient(String id, Client client) {
        callback.run();
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public Set<Client> getClients() {
        return clients;
    }

    @Override
    public void setChangeListener(Runnable callback) {
        this.callback = callback;
    }

}
