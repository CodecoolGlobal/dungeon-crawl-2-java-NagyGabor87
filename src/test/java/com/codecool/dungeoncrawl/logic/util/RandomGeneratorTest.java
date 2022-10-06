package com.codecool.dungeoncrawl.logic.util;

import org.junit.jupiter.api.Test;

import static com.codecool.dungeoncrawl.logic.util.RandomGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {
    @Test
    void testRandomNumberBetween1and100() {
        int randomNum = generateNumberBetween100And1();
        boolean biggerThan0 = randomNum > 0;
        boolean lessThan100 = randomNum < 100;
        assertTrue(biggerThan0 && lessThan100);
    }

    @Test
    void testGenerateRandomStep() {
        int randomStep = generateRandomStep();
        boolean atLeastMinusOne = randomStep >= -1;
        boolean maxTwo = randomStep <= 2;
        assertTrue(atLeastMinusOne && maxTwo);
    }
}