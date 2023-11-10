package com.finalproject.entities.products;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void accountCreation() {
        Account account = new Account("001257", "324", new Date(), AccountType.CHECKING);
        assert account.getProductName().equals("Checking account");
    }

    @Test
    public void withdrawFromAccount() {
        Account account = new Account("001257", "123", new Date(), AccountType.CHECKING);
        assert account.getBalance() == 0;
        account.deposit(100);
        assert account.getBalance() == 100;
        account.withdraw(50);
        assert account.getBalance() == 50;

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(100);
        }, "Insufficient funds");

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100);
        }, "Invalid amount");
    }

    @Test
    public void depositToAccount() {
        Account account = new Account("001257", "214", new Date(), AccountType.CHECKING);
        assert account.getBalance() == 0;
        account.deposit(100);
        assert account.getBalance() == 100;

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100);
        }, "Invalid amount");
    }
}
