package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class ClientSearcher {

    ClientRepository clientRepository;

    Set<Client> clients;

    public ClientSearcher(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientRepository.setChangeListener(this::onRepositoryChange);
    }

    private void onRepositoryChange() {
        reloadDTO();
    }

    private void reloadDTO() {
        clients = clientRepository.getClients();
    }

    public Set<Client> getClientsById(String id) {
        Set<Client> clientsById = new HashSet<>();

        for (Client client : clients) {
            if (client.getId().contains(id))
                clientsById.add(client);
        }

        return clientsById;
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
