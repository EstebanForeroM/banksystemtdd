package com.finalproject.frameworks.repositoryLogic;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class ClientSerializer implements Serializer<Client> {

    public String serialize(Client client) {
        return client.getName() + "," + client.getId() + "," + client.getGender().getGenderName() + ","
                + client.getPassword() + "," + client.getPhotoPath();
    }

    public Client deserialize(String clientString) {
        String[] clientData = clientString.split(",");
        Client client = new Client(clientData[1], clientData[0], Gender.getGenderFromGenderName(clientData[2]),
                clientData[3]);
        client.setPhotoPath(clientData[4]);
        return client;
    }
}
