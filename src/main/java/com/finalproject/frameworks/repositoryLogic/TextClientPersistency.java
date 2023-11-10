package com.finalproject.frameworks.repositoryLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.finalproject.entities.Client;
import com.finalproject.useCases.ClientRepository;

public class TextClientPersistency implements ClientRepository {

    private String filePath;
    private String fileName;
    private ClientSerializer clientSerializer;

    public HashMap<String, String> clients = new HashMap<>();

    public TextClientPersistency(String filePath, Serializer<Client> clientSerializer) {

        clientSerializer = new ClientSerializer();
        fileName = "clients.txt";

        File file = new File(filePath + "/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating file");
            }
        }

        this.filePath = filePath;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] clientData = data.split(",");
                clients.put(clientData[0], data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveClient(Client client) {
        clients.put(client.getId(), clientSerializer.serialize(client));
        saveClients();
    }

    @Override
    public Client getClient(String id) {
        String clientString = clients.get(id);
        if (clientString == null) {
            return null;
        }
        return clientSerializer.deserialize(clientString);
    }

    @Override
    public void deleteClient(String id) {
        clients.remove(id);
        saveClients();
    }

    @Override
    public void updateClient(String id, Client client) {
        clients.put(id, clientSerializer.serialize(client));
        saveClients();
    }

    private void saveClients() {

        try (FileWriter fileWriter = new FileWriter(filePath + "/" + fileName)) {
            fileWriter.write("");
            for (String client : clients.values()) {
                fileWriter.append(client + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
