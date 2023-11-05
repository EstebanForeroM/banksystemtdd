package com.finalproject.frameworks;

import java.io.FileNotFoundException;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;

public class StringSerializer {

    public static String serialize(Client client) {
        StringBuilder sb = new StringBuilder();

        sb.append(client.getId());
        sb.append(",");
        sb.append(client.getName());
        sb.append(",");
        sb.append(client.getGender().getGenderName());
        sb.append(",");
        sb.append(client.getProfilePhotoPath());

        return sb.toString();
    }

    public static Client deserializeClient(String serializedClient) {
        String[] clientData = serializedClient.split(",");

        Client client = new Client(clientData[1], Integer.parseInt(clientData[0]), Gender.fromString(clientData[2]),
                clientData[3]);
        try {
            client.setProfilePhotoPath(clientData[3]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return client;
    }
}
