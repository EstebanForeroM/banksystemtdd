package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class UserCreationService {

    private UserRepository clientRepository;
    private PasswordManager passwordManager;

    Set<String> ids;
    Set<String> passwords;

    public UserCreationService(UserRepository clientRepository, PasswordManager passwordManager) {
        this.clientRepository = clientRepository;
        this.passwordManager = passwordManager;
        ids = new HashSet<String>();
        passwords = new HashSet<String>();
    }

    /*
     * @return id of the created client
     */
    public void createClient(String name, String password, Gender gender, String clientId) {

        comproveId(clientId);

        passwordManager.validatePassword(password);

        Client client = new Client(clientId, name, gender, password);

        clientRepository.saveClient(client);
    }

    public String createClientWithImage(String name, String password, Gender gender, String fileImagePath,
            String clientId) {
        createClient(name, password, gender, clientId);
        Client client = clientRepository.getClient(clientId);
        client.setPhotoPath(fileImagePath);
        clientRepository.updateClient(clientId, client);
        return clientId;
    }

    private void comproveId(String clientId) {
        if (ids.contains(clientId)) {
            throw new RuntimeException("Id already exists");
        }
    }
}
