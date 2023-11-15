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
    public String createClient(String name, String password, Gender gender) {

        passwordManager.validatePassword(password);

        String id = generateId();

        Client client = new Client(id, name, gender, password);

        clientRepository.saveClient(client);

        return id;
    }

    public String createClientWithImage(String name, String password, Gender gender, String fileImagePath) {
        String clientId = createClient(name, password, gender);
        Client client = clientRepository.getClient(clientId);
        client.setPhotoPath(fileImagePath);
        clientRepository.updateClient(clientId, client);
        return clientId;
    }

    private String generateId() {
        String id = generateRandomId();
        while (ids.contains(id)) {
            id = generateRandomId();
        }
        return id;
    }

    private String generateRandomId() {
        StringBuilder id = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        return id.toString();
    }
}
