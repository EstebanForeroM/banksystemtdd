package com.finalproject.frameworks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.ProductManager;

public class TextFileClientRepositoryTest {

    TextFileClientRepository textFileClientRepository;
    ProductManager productManager;

    @BeforeEach
    public void setUp() {
        textFileClientRepository = new TextFileClientRepository();
        productManager = new ProductManager();
        textFileClientRepository.clear();
    }

    @Test
    public void testGetAllClients() {

        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);
        productManager.addProduct(Product.CHECKING_ACCOUNT);

        List<Client> clients = textFileClientRepository.getAllClients();

        assert clients.size() == 0;

        textFileClientRepository.saveClient(new Client("ESSTEBAN", 0214, Gender.MALE, productManager));

        TextFileClientRepository textFileClientRepository2 = new TextFileClientRepository();

        List<Client> clients2 = textFileClientRepository2.getAllClients();

        assert clients2.size() == 1;

        assert clients2.get(0).getName().equals("ESSTEBAN");

        textFileClientRepository.deleteClient(0214);

        TextFileClientRepository textFileClientRepository3 = new TextFileClientRepository();

        List<Client> clients3 = textFileClientRepository3.getAllClients();

        assert clients3.size() == 0;
    }

    @Test
    public void testSaveClient() {
        productManager.addProduct(Product.AMERICAN_EXPRESS_CARD);
        productManager.addProduct(Product.CHECKING_ACCOUNT);
        productManager.addProduct(Product.SAVINGS_ACCOUNT);
        productManager.addProduct(Product.TERM_DEPOSIT_CERTIFICATE);
        productManager.addProduct(Product.VISA_CARD);

        textFileClientRepository.saveClient(new Client("Idk nombre", 15875, Gender.OTHER, productManager));

        List<Client> clients = textFileClientRepository.getAllClients();

        assert clients.size() == 1;

        assert clients.get(0).getName().equals("Idk nombre");

        Iterator<Product> producIterator = clients.get(0).getProductManager().getProductsIterator();

        Set<Product> products = new java.util.HashSet<>();

        products.add(Product.AMERICAN_EXPRESS_CARD);
        products.add(Product.CHECKING_ACCOUNT);
        products.add(Product.SAVINGS_ACCOUNT);
        products.add(Product.TERM_DEPOSIT_CERTIFICATE);
        products.add(Product.VISA_CARD);

        while (producIterator.hasNext()) {
            assert products.contains(producIterator.next());
        }
    }

    @Test
    public void clear() {
        textFileClientRepository.saveClient(new Client("Test1", 1014, Gender.MALE, productManager));

        assertEquals(textFileClientRepository.getAllClients().size(), 1);

        textFileClientRepository.clear();

        assertEquals(textFileClientRepository.getAllClients().size(), 0);
    }

    @Test
    public void deleteClient() {
        textFileClientRepository.saveClient(new Client("Esteban", 1010, Gender.MALE, productManager));

        assertEquals(textFileClientRepository.getAllClients().size(), 1);

        textFileClientRepository.deleteClient(1010);

        assert textFileClientRepository.getAllClients().size() == 0;

        textFileClientRepository.saveClient(new Client("Esteban", 1010, Gender.MALE, productManager));

        assertThrows(RuntimeException.class,
                () -> textFileClientRepository.saveClient(new Client("Esteban", 1010, Gender.MALE, productManager)));

        textFileClientRepository.saveClient(new Client("IDKEWW", 445454545, Gender.FEMALE, productManager));

        assertEquals(textFileClientRepository.getAllClients().size(), 2);

        textFileClientRepository.deleteClient(445454545);

        assertEquals(textFileClientRepository.getAllClients().size(), 1);

        assertEquals(textFileClientRepository.getAllClients().get(0).getName(), "Esteban");

    }
}
