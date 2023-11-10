package com.finalproject.entities.products;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void cardCreation() {
        Card card = new Card("001257", "123", new Date(), CardType.VISA);
        assert card.getProductName().equals("Visa card");
    }

    @Test
    void cardTransactions() {
        Card card = new Card("001257", "123", new Date(), CardType.VISA);
        assert card.getBalance() == 0;
        card.deposit(100);
        assert card.getBalance() == 100;
        card.withdraw(50);
        assert card.getBalance() == 50;

        assertThrows(IllegalArgumentException.class, () -> {
            card.withdraw(100);
        }, "Insufficient funds");

        assertThrows(IllegalArgumentException.class, () -> {
            card.deposit(-100);
        }, "Invalid amount");

        assertThrows(IllegalArgumentException.class, () -> {
            card.withdraw(-100);
        }, "Invalid amount");
    }
}
