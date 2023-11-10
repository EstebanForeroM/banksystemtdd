package com.finalproject.entities.products;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CDTTest {

    @Test
    public void cdtCreation() {
        CDT cdt = new CDT("001257", "123", new Date(), 12);
        assert cdt.getProductName().equals("CDT");

        assert cdt.getExpirationMonths() == 12;
    }
}
