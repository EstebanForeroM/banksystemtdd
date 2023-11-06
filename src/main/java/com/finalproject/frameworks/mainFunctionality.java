package com.finalproject.frameworks;

import com.finalproject.controllers.AppController;
import com.finalproject.controllers.ClientRepository;
import com.finalproject.controllers.ProductRepository;
import com.finalproject.frameworks.repositoryLogic.TextFileClientRepository;
import com.finalproject.frameworks.repositoryLogic.TextFileProductRepository;

public class mainFunctionality {

    public static AppController appController;

    public static void main(String[] args) {
        ClientRepository clientRepository = new TextFileClientRepository("src\\data\\users\\users.txt");
        ProductRepository productRepository = new TextFileProductRepository("src\\data\\products");
        appController = new AppController(clientRepository, productRepository);
    }
}
