package com.finalproject.entities.Products;

import java.util.Date;

import com.finalproject.entities.Product;

public class BankingAccount extends Product {

    private float balance;
    private BankAccountType bankAccountType;

    public BankingAccount(int productId, Date openingDate, float balance, BankAccountType bankAccountType,
            int clientId) {
        super(productId, openingDate, clientId);
        this.balance = balance;
        this.bankAccountType = bankAccountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        if (balance > 0) {
            this.balance = balance;
        }
    }

    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    @Override
    public String getProductName() {
        return bankAccountType.getBankAccountType();
    }

}
