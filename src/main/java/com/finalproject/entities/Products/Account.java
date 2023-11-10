package com.finalproject.entities.products;

import java.util.Date;

import com.finalproject.entities.Product;

public class Account extends Product implements Transactional {

    private AccountType type;

    public Account(String id, String ownerId, Date openingDate, AccountType type) {
        super(id, ownerId, openingDate);
        this.type = type;
    }

    @Override
    public String getProductName() {
        return type.getName() + " account";
    }

    public void deposit(double amount) {
        validateNegativeNumbers(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        validateNegativeNumbers(amount);
        if (balance < amount)
            throw new IllegalArgumentException("Insufficient funds");

        balance -= amount;
    }

    private void validateNegativeNumbers(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Invalid amount");
    }
}
