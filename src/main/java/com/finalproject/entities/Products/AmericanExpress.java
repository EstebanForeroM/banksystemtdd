package com.finalproject.entities.Products;

import java.io.Serializable;

public class AmericanExpress implements CardBrand, Serializable {

    @Override
    public String getCardBrand() {
        return "American Express";
    }

}
