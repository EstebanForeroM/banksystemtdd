package com.finalproject.entities.Products;

import java.io.Serializable;

public class Visa implements CardBrand, Serializable {

    @Override
    public String getCardBrand() {
        return "Visa";
    }

}
