package com.finalproject.frameworks;

import com.finalproject.controllers.ProductRepository;
import com.finalproject.entities.Product;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileProductRepository implements ProductRepository {
    private static final Logger LOGGER = Logger.getLogger(TextFileProductRepository.class.getName());
    private final String directoryPath;

    public TextFileProductRepository(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    private String getFilePathForProduct(int productId) {
        return Paths.get(directoryPath, productId + ".ser").toString();
    }

    @Override
    public void clear() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : directoryStream) {
                Files.delete(path);
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to clear the repository", e);
        }
    }

    @Override
    public void saveProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        String filePath = getFilePathForProduct(product.getProductId());
        File file = new File(filePath);
        boolean exists = file.exists();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false))) {
            oos.writeObject(product);
            if (!exists) {
                LOGGER.log(Level.INFO, "Created new product file for product ID: " + product.getProductId());
            } else {
                LOGGER.log(Level.INFO, "Overwritten existing product file for product ID: " + product.getProductId());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save product", e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            Files.deleteIfExists(Paths.get(getFilePathForProduct(id)));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to delete product", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : directoryStream) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                    Product product = (Product) ois.readObject();
                    products.add(product);
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.log(Level.WARNING, "Failed to read product from file: " + path, e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load products", e);
        }
        return products;
    }
}
