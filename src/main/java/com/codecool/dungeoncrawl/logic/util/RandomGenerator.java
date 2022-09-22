package com.codecool.dungeoncrawl.logic.util;

import java.util.Random;

public class RandomGenerator {
    public static int generateNumberBetween100And1() {
        Random random = new Random();
        return random.nextInt((100) + 1);
    }

    public static int generateRandomStep(){
        int max = 2;
        int min = -1;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
