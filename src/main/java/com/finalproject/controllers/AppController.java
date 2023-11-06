package com.finalproject.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.Products.ProductsTypes;
import com.finalproject.useCases.ClientManager;
import com.finalproject.useCases.ProductManager;

public class AppController {

    private final ClientRepository clientRepository;

    private final ClientManager clientManager;

    private final ProductRepository productRepository;

    private final ProductManager productManager;

    /*
     * functions to be implemented:
     * 
     * findClientById(int id) -> return client
     * findClientsByName(String name) -> return clients
     * findClientsByGender(Gender gender) -> return clients
     * 
     * findProductById(int id) -> return product
     * findProductsByProductName(String name) -> return products
     * findProductsByClientId(int id) -> return products
     * 
     */

    public AppController(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productManager = new ProductManager(productRepository.getAllProducts());
        clientManager = new ClientManager(clientRepository.getAllClients());
    }

    public void addProductToClient(int clientId, Product product) throws FileNotFoundException {
        ;
        clientRepository.saveClient(getClientById(clientId));
    }

    public Client findClientById(int id) {
        return clientManager.getClientById(id);
    }

    public List<Client> findClientsByName(String name) {
        return clientManager.getClientsByName(name);
    }

    public List<Client> findClientsByGender(Gender gender) {
        return clientManager.getClientsByGender(gender);
    }

    public Product findProductById(int id) {
        return productManager.getProductByID(id);
    }

    public List<Product> findProductsByProductType(ProductsTypes productType) {
        return productManager.getProductsByType(productType);
    }

    public List<Product> findProductsByClientId(int id) {
        return productManager.getProductsByClientID(id);
    }

    public void addClient(Client client) throws FileNotFoundException {
        clientManager.addClient(client);
        clientRepository.saveClient(client);

        if (client.getProductManager().getProducts().size() > 0) {
            throw new IllegalArgumentException("Please use the addProduct to add products to the client.");
        }
    }

    public void addProduct(int clientId, Product product) throws FileNotFoundException {
        productManager.addProduct(product);
        clientManager.getClientById(clientId).getProductManager().addProduct(product);
        productRepository.saveProduct(product);
    }

    public void deleteClient(int id) {
        clientManager.removeClient(id);
        productManager.getProductsByClientID(id)
                .forEach(product -> productManager.removeProduct(product.getProductId()));
        clientRepository.deleteClient(id);
    }

    public void updateClient(Client client) throws FileNotFoundException {
        clientManager.updateClient(client);
        clientRepository.saveClient(client);
    }

    public void updateProduct(Product product) throws FileNotFoundException {
        if (productManager.getProductByID(product.getProductId()) == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if (productManager.getProductByID(product.getProductId()).getClientId() != product.getClientId())
            throw new IllegalArgumentException(
                    "The client Id of the given product isn't the same as the product's client Id.");

        productManager.removeProduct(product.getProductId());
        clientManager.getClientById(product.getClientId()).getProductManager().addProduct(product);
        productManager.addProduct(product);
        productRepository.saveProduct(product);
    }

    public void deleteProduct(int id) {
        productManager.removeProduct(id);
        productRepository.deleteProduct(id);
    }

    public Client getClientById(int id) {
        return clientManager.getClientById(id);
    }

    public void getAllClients() {
        clientManager.getClients();
    }

    public void getClientByName(String name) {
        clientManager.getClientsByName(name);
    }

}
