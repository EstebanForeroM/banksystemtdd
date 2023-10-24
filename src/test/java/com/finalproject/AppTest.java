package com.finalproject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSumNums() {
        App app = new App();
        assertTrue(app.sumNums(1, 2) == 3);
    }
}
