package com.finalproject.frameworks.repositoryLogic;

import com.finalproject.controllers.ClientRepository;
import com.finalproject.entities.Client;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class TextFileClientRepository implements ClientRepository {

    private final Path filePath;

    public TextFileClientRepository(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    @Override
    public void clear() {
        try {
            Files.write(filePath, new byte[0]);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clear the client repository", e);
        }
    }

    @Override
    public void saveClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        // Read all clients
        List<Client> clients = getAllClients();

        // Remove existing client with the same ID
        clients.removeIf(c -> c.getId() == client.getId());

        // Add the new client
        clients.add(client);

        // Clear the file before re-writing
        clear();

        // Save all clients back to the file
        for (Client c : clients) {
            String serializedClient = StringSerializer.serialize(c) + System.lineSeparator();
            try {
                Files.write(filePath, serializedClient.getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save the client", e);
            }
        }
    }

    @Override
    public void deleteClient(int id) {
        List<Client> clients = getAllClients();
        clients.removeIf(client -> client.getId() == id);
        clear(); // Clear the file before re-writing
        clients.forEach(this::saveClient);
    }

    @Override
    public List<Client> getAllClients() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try {
            return Files.lines(filePath)
                    .map(StringSerializer::deserializeClient)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the clients", e);
        }
    }
}
