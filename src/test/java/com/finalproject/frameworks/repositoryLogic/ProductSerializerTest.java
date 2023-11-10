package com.finalproject.frameworks.repositoryLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.finalproject.entities.Product;
import com.finalproject.entities.products.Account;
import com.finalproject.entities.products.AccountType;
import com.finalproject.entities.products.CDT;
import com.finalproject.entities.products.Card;
import com.finalproject.entities.products.CardType;
import com.finalproject.entities.products.ProductType;
import com.finalproject.entities.products.UninitializedProduct;

import java.util.Date;

public class ProductSerializerTest {

    @Test
    public void testSerializeAndDeserialize() {
        // Arrange
        ProductSerializer serializer = new ProductSerializer();
        Product originalProduct = new CDT("1564", "5", new Date(), 12);

        // Act
        String serializedProduct = serializer.serialize(originalProduct);
        Product deserializedProduct = serializer.deserialize(serializedProduct);

        // Assert
        assertTrue(deserializedProduct instanceof CDT);
        assertEquals(originalProduct.getId(), deserializedProduct.getId());
        assertEquals(originalProduct.getOwnerId(), deserializedProduct.getOwnerId());
        // ! assertEquals(originalProduct.getOpeningDate(),
        // deserializedProduct.getOpeningDate());
    }

    @Test
    public void testSerialize() {
        // Arrange
        ProductSerializer serializer = new ProductSerializer();
        Product originalUninitializedProduct = new UninitializedProduct("1", "42",
                ProductType.UninitializedProduct);
        Product originalCDT = new CDT("2", "234", new Date(), 12);
        Product originalVisaCard = new Card("3", "234", new Date(), CardType.VISA);
        Product originalMasterCard = new Card("4", "234", new Date(), CardType.MASTERCARD);
        Product originalCheckingAccount = new Account("5", "234", new Date(), AccountType.CHECKING);
        Product originalSavingsAccount = new Account("6", "234", new Date(), AccountType.SAVINGS);

        // Act and Assert
        testProduct(serializer, originalUninitializedProduct);
        testProduct(serializer, originalCDT);
        testProduct(serializer, originalVisaCard);
        testProduct(serializer, originalMasterCard);
        testProduct(serializer, originalCheckingAccount);
        testProduct(serializer, originalSavingsAccount);
    }

    private void testProduct(ProductSerializer serializer, Product originalProduct) {
        String serializedProduct = serializer.serialize(originalProduct);
        Product deserializedProduct = serializer.deserialize(serializedProduct);

        assertEquals(originalProduct.getId(), deserializedProduct.getId());
        assertEquals(originalProduct.getOwnerId(), deserializedProduct.getOwnerId());
    }

    @Test
    public void testSerializationUninitializedProduct() {
        // Arrange
        ProductSerializer serializer = new ProductSerializer();
        Product originalProduct = new UninitializedProduct("1", "42", ProductType.UninitializedProduct);

        // Act
        String serializedProduct = serializer.serialize(originalProduct);
        Product deserializedProduct = serializer.deserialize(serializedProduct);

        // Assert
        assertTrue(deserializedProduct instanceof UninitializedProduct);
        assertEquals(originalProduct.getId(), deserializedProduct.getId());
        assertEquals(originalProduct.getOwnerId(), deserializedProduct.getOwnerId());
    }
}