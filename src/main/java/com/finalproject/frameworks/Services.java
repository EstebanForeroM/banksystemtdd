package com.finalproject.frameworks;

import com.finalproject.useCases.UserRepository;
import com.finalproject.useCases.UserSearcher;
import com.finalproject.frameworks.repositoryLogic.ClientSerializer;
import com.finalproject.frameworks.repositoryLogic.ProductSerializer;
import com.finalproject.frameworks.repositoryLogic.TextClientPersistency;
import com.finalproject.frameworks.repositoryLogic.TextProductPersistency;
import com.finalproject.frameworks.validations.TokenGenerator;
import com.finalproject.frameworks.validations.TokenValidator;
import com.finalproject.useCases.PasswordManager;
import com.finalproject.useCases.ProductCreationService;
import com.finalproject.useCases.ProductModificationService;
import com.finalproject.useCases.ProductRepository;
import com.finalproject.useCases.ProductSearcher;
import com.finalproject.useCases.TokenAuthenticationService;
import com.finalproject.useCases.UserCreationService;
import com.finalproject.useCases.UserModificationService;

public class Services {

    private UserRepository clientRepository;
    private ProductRepository productRepository;
    private PasswordManager passwordManager;

    public static TokenAuthenticationService tokenAuthenticationService;
    public static UserSearcher userSearcher;
    public static UserCreationService userCreationService;
    public static UserModificationService userModificationService;
    public static ProductSearcher productSearcher;
    public static ProductModificationService productModificationService;
    public static ProductCreationService productCreationService;

    public Services() {
        clientRepository = new TextClientPersistency("src\\data\\users", new ClientSerializer());
        productRepository = new TextProductPersistency("src\\data\\products", new ProductSerializer());
        tokenAuthenticationService = new TokenGenerator(new TokenValidator(), clientRepository);
        passwordManager = new PasswordManager(clientRepository);
        userSearcher = new UserSearcher(clientRepository);
        userCreationService = new UserCreationService(clientRepository, passwordManager);
        userModificationService = new UserModificationService(tokenAuthenticationService, clientRepository,
                passwordManager);
        productSearcher = new ProductSearcher(productRepository);
        productModificationService = new ProductModificationService(productRepository);
        productCreationService = new ProductCreationService(productRepository, tokenAuthenticationService,
                productSearcher);
    }
}
