package com.finalproject.entities.Products;

import java.io.Serializable;

public class SavingsAccount implements BankAccountType, Serializable {

    @Override
    public String getBankAccountType() {
        return "Savings Account";
    }

}
