package com.finalproject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class SetUpAppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSumNums() {
        SetUpApp app = new SetUpApp();
        assertTrue(app.sumNums(1, 2) == 3);
    }
}
