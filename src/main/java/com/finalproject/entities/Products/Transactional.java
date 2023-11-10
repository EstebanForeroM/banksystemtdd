package com.finalproject.entities.products;

public interface Transactional {
    void deposit(double amount);

    void withdraw(double amount);
}