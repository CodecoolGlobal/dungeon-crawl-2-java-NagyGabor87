package com.codecool.dungeoncrawl.logic;

public enum Level {
    MAP_1("/map_files/map_1.txt"),
    MAP_2("/map_files/map2.txt"),
    MAP_3("/map_files/map3.txt"),
    MAP_4("/map_files/map4.txt"),

    MENU("/map_files/menu.txt");

    private final String mapLevel;
    Level(String mapLevel) {
        this.mapLevel = mapLevel;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public Level nextLevel() {
        switch (this) {
            case MAP_1: return MAP_2;
            case MAP_2: return MAP_3;
            case MAP_3: return MAP_4;

            default: return MENU;
        }

    }
}
