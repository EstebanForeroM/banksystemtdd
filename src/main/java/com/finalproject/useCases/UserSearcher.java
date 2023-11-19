package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class UserSearcher {

    UserRepository clientRepository;

    Set<Client> clients;

    public UserSearcher(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientRepository.setChangeListener(this::onRepositoryChange);
    }

    private void onRepositoryChange() {
        reloadDTO();
    }

    private void reloadDTO() {
        clients = clientRepository.getClients();
    }

    public Client getClientById(String id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        throw new RuntimeException("Client not found");
    }

    public Set<Client> getClientsById(String id) {
        Set<Client> clientsById = new HashSet<>();

        for (Client client : clients) {
            if (client.getId().contains(id))
                clientsById.add(client);
        }

        return clientsById;
    }

    public boolean userExists(String id) {
        return !getClientsById(id).isEmpty();
    }

    public Set<Client> getClientsByName(String name) {
        Set<Client> clientsByName = new HashSet<>();

        for (Client client : clients) {
            if (client.getName().contains(name))
                clientsByName.add(client);
        }

        return clientsByName;
    }

    public Set<Client> getClientsByGender(Gender gender) {
        Set<Client> clientsByGender = new HashSet<>();

        for (Client client : clients) {
            if (client.getGender() == gender)
                clientsByGender.add(client);
        }

        return clientsByGender;
    }
}
