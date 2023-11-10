package com.finalproject.entities.products;

import org.junit.jupiter.api.Test;

public class UninitializedProductTest {

    @Test
    public void uninitializedProductCreation() {
        UninitializedProduct uninitializedProduct = new UninitializedProduct("123", "123", ProductType.VISA_CARD);
        assert uninitializedProduct.getProductName().equals("Visa card");

        assert uninitializedProduct.getProductType() == ProductType.VISA_CARD;
    }
}
