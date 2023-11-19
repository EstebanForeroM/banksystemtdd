package com.finalproject.useCases;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.Card;
import com.finalproject.entities.products.CardType;

public class UserSearcherTest {
    UserRepository userRepository;
    UserSearcher userSearcher;

    @BeforeEach
    public void setUp() {
        userRepository = new FakeClientRepository();
        userSearcher = new UserSearcher(userRepository);
    }

    @Test
    public void testGetUsersFromProducts() {
        userRepository.saveClient(new Client("24324", "Esteban", Gender.MALE, "wefsdaff"));

        userRepository.saveClient(new Client("4234", "Juan", Gender.MALE, "wefsdaff"));

        Set<Product> products = Set.of(new Card("324", "24324", new Date(), CardType.VISA),
                new Card("3224", "24324", new Date(), CardType.VISA));

        Set<Client> users = userSearcher.getClientsFromProducts(products);

        assert (users.size() == 1);
    }
}
