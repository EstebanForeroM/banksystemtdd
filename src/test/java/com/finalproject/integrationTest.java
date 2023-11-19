package com.finalproject;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Gender;
import com.finalproject.entities.products.ProductType;
import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;

public class integrationTest {

    @BeforeAll
    public static void setup() {
        new Services();
    }

    @Test
    public void test() {
        Services.userCreationService.createClient("Esteban", "estebj", Gender.MALE);
        assertThrows(IllegalArgumentException.class, () -> {
            Services.userCreationService.createClient("idksad", "estebj", Gender.OTHER);
        });

        Token token = Services.tokenAuthenticationService.getToken("estebj");

        String productId = Services.productCreationService.addProduct(token, ProductType.MASTERCARD);
        assertThrows(RuntimeException.class, () -> {
            Services.productCreationService.addProduct(token, ProductType.MASTERCARD);
        });

        assertThrows(RuntimeException.class, () -> {
            Services.productCreationService.initializeCDT(productId, new Date(), 12);
        });

        Services.productCreationService.initializeCard(productId, new Date());

        ProductType productType = ProductType.CDT;

        Services.userSearcher.getClientsFromProducts(Services.productSearcher.getProductsByType(productType));
    }
}
