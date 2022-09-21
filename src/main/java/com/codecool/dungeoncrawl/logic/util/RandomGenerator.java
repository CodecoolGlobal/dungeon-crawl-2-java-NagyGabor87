package com.codecool.dungeoncrawl.logic.util;

import java.util.Random;

public class RandomGenerator {
    public static int generateNumberBetween100And1() {
        Random random = new Random();
        return random.nextInt((100) + 1);
    }

}
