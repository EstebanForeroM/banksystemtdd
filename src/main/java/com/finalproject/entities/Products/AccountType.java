package com.finalproject.entities.products;

public enum AccountType {
    SAVINGS("Savings"),
    CHECKING("Checking");

    private String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
