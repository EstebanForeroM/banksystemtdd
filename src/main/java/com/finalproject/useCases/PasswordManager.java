package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Client;

public class PasswordManager {
    Set<String> passwords;
    private ClientRepository clientRepository;

    public PasswordManager(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        passwords = new HashSet<>();
        clientRepository.setChangeListener(this::OnRepositoryChange);
    }

    private void OnRepositoryChange() {
        passwords.clear();
        Set<Client> clients = clientRepository.getClients();

        for (Client client : clients) {
            passwords.add(client.getPassword());
        }
    }

    public boolean validatePassword(String password) {
        if (password == null)
            throw new RuntimeException("Password cannot be null");
        return passwords.contains(password);
    }
}
