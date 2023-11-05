package com.finalproject.entities;

import java.io.Serializable;
import java.util.Date;

public abstract class Product implements Serializable {
    protected final int productId;
    protected Date openingDate;
    private final int clientId;

    private Runnable onDeleteClient;

    public Product(int productId, Date openingDate, int clientId) {
        this.productId = productId;
        this.openingDate = openingDate;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        if (openingDate == null)
            throw new IllegalArgumentException("Opening date cannot be null");

        this.openingDate = openingDate;
    }

    public void OnDeleteClient(Runnable onDeleteClient) {
        this.onDeleteClient = onDeleteClient;
    }

    public void deleteClient() {
        if (onDeleteClient != null)
            onDeleteClient.run();
    }

    abstract public String getProductName();
}