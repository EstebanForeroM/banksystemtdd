package com.finalproject.entities;

public enum Product {
    SAVINGS_ACCOUNT("Savings Account"),
    CHECKING_ACCOUNT("Checking Account"),
    TERM_DEPOSIT_CERTIFICATE("Term Deposit Certificate"),
    VISA_CARD("Visa Card"),
    AMERICAN_EXPRESS_CARD("American Express Card");

    private String productName;

    Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}