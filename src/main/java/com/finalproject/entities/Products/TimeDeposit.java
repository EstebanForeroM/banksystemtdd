package com.finalproject.entities.Products;

import java.util.Date;

import com.finalproject.entities.Product;

public class TimeDeposit extends Product {
    private String maturityDate;
    private float balance;

    public TimeDeposit(int productId, Date openingDate, String maturityDate, float balance, int clientId) {
        super(productId, openingDate, clientId);
        this.maturityDate = maturityDate;
        this.balance = balance;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public float getBalance() {
        return balance;
    }

    @Override
    public String getProductName() {
        return "Time Deposit";
    }
}
