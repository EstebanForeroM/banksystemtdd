package com.finalproject.frameworks.repositoryLogic;

import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.useCases.UserRepository;

public class TextClientPersistency implements UserRepository {

    private TextPersistency<Client> textPersistency;

    public TextClientPersistency(String filePath, Serializer<Client> serializer) {
        textPersistency = new TextPersistency<>(filePath, "clients.txt", serializer);
    }

    @Override
    public void saveClient(Client client) {
        textPersistency.saveObject(client);
    }

    @Override
    public Client getClient(String id) {
        return textPersistency.getObject(id);
    }

    @Override
    public void deleteClient(String id) {
        textPersistency.deleteObject(id);
    }

    @Override
    public void updateClient(String id, Client client) {
        textPersistency.updateObject(id, client);
    }

    @Override
    public Set<Client> getClients() {
        return textPersistency.getObjectsList();
    }

    @Override
    public void setChangeListener(Runnable callback) {
        textPersistency.addChangeListener(callback);
    }

}
