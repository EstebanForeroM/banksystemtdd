package com.finalproject.entities.Products;

import java.util.Date;

import com.finalproject.entities.Product;

public class Card extends Product {

    private CardBrand cardBrand;
    private float creditLimit;

    public Card(int productId, Date openingDate, CardBrand cardBrand, float creditLimit, int clientId) {
        super(productId, openingDate, clientId);
        this.cardBrand = cardBrand;
        this.creditLimit = creditLimit;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        if (creditLimit > 0) {
            this.creditLimit = creditLimit;
        }
    }

    @Override
    public String getProductName() {
        return cardBrand.getCardBrand() + " " + "Card";
    }
}
