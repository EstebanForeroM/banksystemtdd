package com.finalproject.useCases;

import java.util.Random;
import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class UserCreationService {

    private ClientRepository clientRepository;
    private PasswordManager passwordManager;

    Set<String> ids;
    Set<String> passwords;

    UserCreationService(ClientRepository clientRepository, PasswordManager passwordManager) {
        this.clientRepository = clientRepository;
        this.passwordManager = passwordManager;
    }

    /*
     * @return id of the created client
     */
    public String createClient(String name, String password, Gender gender) {

        if (passwordManager.validatePassword(password)) {
            throw new RuntimeException("Password already exists");
        }

        String id = generateId();

        Client client = new Client(id, name, gender, password);

        clientRepository.saveClient(client);

        return id;
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
