package com.finalproject.entities.products;

import java.util.Date;

import com.finalproject.entities.Product;

public class CDT extends Product {

    private int expirationMonths;

    public CDT(String id, String ownerId, Date openingDate, int expirationMonths) {
        super(id, ownerId, openingDate);
        validateNegativeNumbers(expirationMonths);
        this.expirationMonths = expirationMonths;
    }

    @Override
    public String getProductName() {
        return "CDT";
    }

    public int getExpirationMonths() {
        return expirationMonths;
    }

    public void setExpirationMonths(int expirationMonths) {
        validateNegativeNumbers(expirationMonths);
        this.expirationMonths = expirationMonths;
    }

    private void validateNegativeNumbers(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Invalid amount");
    }
}
