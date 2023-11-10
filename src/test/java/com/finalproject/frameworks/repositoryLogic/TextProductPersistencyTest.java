package com.finalproject.frameworks.repositoryLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Product;
import com.finalproject.entities.products.Card;
import com.finalproject.entities.products.CardType;
import com.finalproject.entities.products.ProductType;
import com.finalproject.entities.products.UninitializedProduct;

public class TextProductPersistencyTest {

    private TextProductPersistency textProductPersistency;

    List<Product> products;

    @BeforeEach
    public void setUp() {
        textProductPersistency = new TextProductPersistency("src\\data\\products", new ProductSerializer());
        products = new ArrayList<>();

        products.add(new Card("001257", "123", new Date(), CardType.VISA));
        products.add(new Card("001258", "123", new Date(), CardType.MASTERCARD));
        products.add(new Card("001259", "123", new Date(), CardType.VISA));
        products.add((new UninitializedProduct("001260", "123", ProductType.MASTERCARD)));
    }

    @Test
    public void testProductSaving() {
        for (Product product : products) {
            textProductPersistency.saveProduct(product);
        }

        assert textProductPersistency.getProduct("001257") != null;
        assert textProductPersistency.getProduct("001258") instanceof Card;
        assert textProductPersistency.getProduct("001259").getProductName().equals("Visa card");

        assert textProductPersistency.getProduct("001260") instanceof UninitializedProduct;

        textProductPersistency.updateProduct("001257", new Card("001257", "123", new Date(), CardType.MASTERCARD));

        for (Product product : products) {
            textProductPersistency.deleteProduct(product.getId());
        }
    }

    @Test
    public void productUpdating() {
        for (Product product : products) {
            textProductPersistency.saveProduct(product);
        }

        assert textProductPersistency.getProduct("001257") != null;

        textProductPersistency.updateProduct("001257", new Card("001257", "1533", new Date(), CardType.MASTERCARD));

        assert textProductPersistency.getProduct("001257").getOwnerId().equals("1533");
        assert textProductPersistency.getProduct("001257").getProductName().equals("Mastercard card");
    }
}
