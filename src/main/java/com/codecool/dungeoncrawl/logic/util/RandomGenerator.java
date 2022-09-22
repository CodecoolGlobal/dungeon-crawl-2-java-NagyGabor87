package com.codecool.dungeoncrawl.logic.util;

import java.util.Random;

public class RandomGenerator {
    private final static int MIN_DAMAGE_MODIFIER = -1;
    private final static int MAX_DAMAGE_MODIFIER = 1;
    private final static int MIN_HEALTH_MODIFIER = -2;
    private final static int MAX_HEALTH_MODIFIER = 2;


    public static int generateNumberBetween100And1() {
        return randomNumberBetweenMinMax(1, 100);
    }

    public static int generateRandomStep(){
        int max = 2;
        int min = -1;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static int generateRandomDamageModifier() {
        return randomNumberBetweenMinMax(MIN_DAMAGE_MODIFIER, MAX_DAMAGE_MODIFIER);
    }

    public static int generateRandomHealthModifier() {
        return randomNumberBetweenMinMax(MIN_HEALTH_MODIFIER, MAX_HEALTH_MODIFIER);
    }

    private static int randomNumberBetweenMinMax(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

}
