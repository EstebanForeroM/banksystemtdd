package com.finalproject.useCases;

import java.util.HashSet;
import java.util.Set;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class UserCreationService {

    private UserRepository clientRepository;
    private PasswordManager passwordManager;
    Set<String> ids;

    public UserCreationService(UserRepository clientRepository, PasswordManager passwordManager) {
        this.clientRepository = clientRepository;
        this.passwordManager = passwordManager;
        ids = new HashSet<String>();
    }

    /*
     * @return id of the created client
     */
    public void createClient(String name, String password, Gender gender, String clientId, String fileImagePath) {
        validateParametersNotNull(clientId, name, gender, password, fileImagePath);
        comproveId(clientId);
        passwordManager.validatePassword(password);
        Client client = new Client(clientId, name, gender, password);
        client.setPhotoPath(fileImagePath);
        clientRepository.saveClient(client);
        ids.add(clientId);
    }

    private void validateParametersNotNull(Object... parameters) {
        for (Object parameter : parameters) {
            if (parameter == null) {
                throw new RuntimeException("Parameters cannot be null");
            }
        }
    }

    private void comproveId(String clientId) {
        if (ids.contains(clientId)) {
            throw new RuntimeException("Id already exists");
        }
    }

}
