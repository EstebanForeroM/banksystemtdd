package com.finalproject.entities.Products;

public enum ProductsTypes {
    SAVINGS_ACCOUNT("Savings Account"),
    CHECKING_ACCOUNT("Checking Account"),
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    VISA("Visa"),
    MASTERCARD("Mastercard"),
    AMERICAN_EXPRESS("American Express"),
    DISCOVER("Discover");

    private String type;

    ProductsTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
