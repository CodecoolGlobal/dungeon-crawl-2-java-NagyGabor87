package com.codecool.dungeoncrawl.logic;

public enum LEVEL {
    START("/map.txt"),
    END("/exit.txt");

    private final String mapLevel;
    LEVEL(String mapLevel) {
        this.mapLevel = mapLevel;
    }

    public String getMapLevel() {
        return mapLevel;
    }
}
