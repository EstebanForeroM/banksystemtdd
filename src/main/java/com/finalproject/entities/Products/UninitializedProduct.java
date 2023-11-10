package com.finalproject.entities.products;

import java.util.Date;

import com.finalproject.entities.Product;

public class UninitializedProduct extends Product {

    private ProductType productType;

    public UninitializedProduct(String id, String ownerId, ProductType productName) {
        super(id, ownerId, new Date());
        this.productType = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    @Override
    public String getProductName() {
        return "Uninitialized product";
    }

}
