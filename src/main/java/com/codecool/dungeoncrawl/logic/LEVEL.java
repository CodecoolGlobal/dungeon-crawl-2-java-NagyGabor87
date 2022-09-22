package com.codecool.dungeoncrawl.logic;

public enum LEVEL {
    START("/map_files/map.txt"),
    MAP_2("/map_files/map2.txt"),
    MAP_3("/map_files/map3.txt"),
    MAP_4("/map_files/map4.txt"),

    END("/map_files/exit.txt");

    private final String mapLevel;
    LEVEL(String mapLevel) {
        this.mapLevel = mapLevel;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public LEVEL nextLevel() {
        switch (this) {
            case START: return MAP_2;
            case MAP_2: return MAP_3;
            case MAP_3: return MAP_4;
            case MAP_4: return END;

            default: return START;
        }

    }
}
