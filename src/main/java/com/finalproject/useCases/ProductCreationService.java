package com.finalproject.useCases;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import com.finalproject.entities.products.Account;
import com.finalproject.entities.products.AccountType;
import com.finalproject.entities.products.CDT;
import com.finalproject.entities.products.Card;
import com.finalproject.entities.products.CardType;
import com.finalproject.entities.products.ProductType;
import com.finalproject.entities.products.UninitializedProduct;

public class ProductCreationService {
    Set<String> productIds;

    ProductRepository productRepository;
    private TokenAuthenticationService tokenAuthenticationService;

    ProductCreationService(ProductRepository productRepository, TokenAuthenticationService tokenAuthenticationService) {
        this.productRepository = productRepository;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    public String addProduct(Token token, ProductType productName) {
        if (tokenAuthenticationService.validate(token)) {
            String productId = generateId();
            UninitializedProduct uninitializedProduct = new UninitializedProduct(productId, token.getClientId(),
                    productName);
            productRepository.saveProduct(uninitializedProduct);
            return productId;
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    public String initializeCard(UninitializedProduct uninitializedProduct, Date date) {
        String productId = uninitializedProduct.getId();
        Card card = null;
        for (CardType cardType : CardType.values()) {
            if (uninitializedProduct.getProductName().contains(cardType.getName())) {
                card = new Card(productId, "1233", date, cardType);
            }
        }

        if (card == null)
            throw new RuntimeException("The provided type does not exist");

        productRepository.updateProduct(productId, card);

        return productId;
    }

    public String initializeCDT(UninitializedProduct uninitializedProduct, Date date, int expirationMonths) {
        String productId = uninitializedProduct.getId();
        CDT cdt = null;
        if (uninitializedProduct.getProductType().getName().contains("CDT")) {
            cdt = new CDT(productId, uninitializedProduct.getOwnerId(), date, expirationMonths);
        } else {
            throw new RuntimeException("Invalid product type");
        }
        productRepository.updateProduct(productId, cdt);
        return productId;
    }

    public String initializeAccount(UninitializedProduct uninitializedProduct, Date date) {
        String productId = uninitializedProduct.getId();

        Account account = null;
        for (AccountType accountType : AccountType.values()) {
            if (uninitializedProduct.getProductName().contains(accountType.getName())) {
                account = new Account(productId, "1233", date, accountType);
            }
        }

        if (account == null)
            throw new RuntimeException("The provided type does not exist");

        productRepository.updateProduct(productId, account);

        return productId;
    }

    public void createProduct() {

    }

    private String generateId() {
        String id = generateRandomId();
        while (productIds.contains(id)) {
            id = generateRandomId();
        }
        return id;
    }

    private String generateRandomId() {
        StringBuilder id = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        return id.toString();
    }
}
