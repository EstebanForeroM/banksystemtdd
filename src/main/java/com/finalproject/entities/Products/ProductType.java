package com.finalproject.entities.products;

public enum ProductType {
    CDT("CDT"),
    VISA_CARD("Visa card"),
    MASTERCARD("Mastercard card"),
    AMERICAN_EXPRESS("American Express card"),
    CHECKING_ACCOUNT("Checking account"),
    SAVINGS_ACCOUNT("Savings account"),
    UninitializedProduct("Uninitialized product");

    private String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductType getProductType(String name) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getName().equals(name)) {
                return productType;
            }
        }
        return ProductType.UninitializedProduct;
    }
}
