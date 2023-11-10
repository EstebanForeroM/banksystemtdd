package com.finalproject.frameworks.repositoryLogic;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class ClientSerializer implements Serializer<Client> {

    public String serialize(Client client) {
        return client.getId() + "," + client.getName() + "," + client.getGender().getGenderName() + ","
                + client.getPassword();
    }

    public Client deserialize(String clientString) {
        String[] clientData = clientString.split(",");
        return new Client(clientData[0], clientData[1], Gender.getGenderFromGenderName(clientData[2]), clientData[3]);
    }
}
