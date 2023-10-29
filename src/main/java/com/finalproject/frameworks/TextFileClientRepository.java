package com.finalproject.frameworks;

import com.finalproject.controllers.ClientRepository;
import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.ProductManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileClientRepository implements ClientRepository {

    private final String fileName;

    public TextFileClientRepository() {
        fileName = "src\\lib\\users\\users.txt";
    }

    @Override
    public void saveClient(Client client) {
        List<Client> clients = getAllClients();
        for (Client client2 : clients) {
            if (client2.getId() == client.getId()) {
                throw new RuntimeException("Client with the same id already exists");
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(serializeClient(client));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error while saving the client to the file", e);
        }
    }

    @Override
    public void deleteClient(int id) {
        List<Client> clients = getAllClients();
        boolean removed = clients.removeIf(client -> client.getId() == id);
        if (removed) {
            overwriteClients(clients);
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Client client = deserializeClient(line);
                clients.add(client);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading clients from the file", e);
        }

        return clients;
    }

    @Override
    public void clear() {
        try {
            new PrintWriter(fileName).close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error while clearing the clients file", e);
        }
    }

    private String serializeClient(Client client) {
        return client.getId() + "," +
                client.getName() + "," +
                client.getGender() + "," +
                client.getProfilePhotoPath() + "," +
                serializeProductManager(client.getProductManager());
    }

    private String serializeProductManager(ProductManager productManager) {
        StringBuilder productsBuilder = new StringBuilder();
        productManager.getProductsIterator().forEachRemaining(product -> {
            productsBuilder.append(product.name()).append(";");
        });
        // Remove the last semicolon
        if (productsBuilder.length() > 0) {
            productsBuilder.setLength(productsBuilder.length() - 1);
        }
        return productsBuilder.toString();
    }

    private Client deserializeClient(String line) throws FileNotFoundException {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        Gender gender = Gender.valueOf(parts[2]);
        ProductManager productManager;
        if (parts.length == 4)
            productManager = new ProductManager();
        else
            productManager = deserializeProductManager(parts[4]);
        Client client = new Client(name, id, gender, productManager);
        try {
            client.setProfilePhotoPath(parts[3]);
        } catch (FileNotFoundException e) {
            client.setProfilePhotoPath("src\\lib\\img\\default.png");
        }
        return client;
    }

    private ProductManager deserializeProductManager(String serializedProductManager) {
        List<String> productNames = Arrays.asList(serializedProductManager.split(";"));
        ProductManager productManager = new ProductManager();
        for (String productName : productNames) {
            productManager.addProduct(Product.valueOf(productName));
        }
        return productManager;
    }

    private void overwriteClients(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Client client : clients) {
                writer.write(serializeClient(client));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while overwriting the clients in the file", e);
        }
    }
}
