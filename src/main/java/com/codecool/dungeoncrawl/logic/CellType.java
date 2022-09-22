package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    SPIDERWEB("spiderweb"),
    TORCH("torch"),
    HELMET("helmet"),
    KEY("key"),
    CLOSED_DOOR("closed_door"),
    OPEN_DOOR("open_door"),
    GHOST("ghost"),
    BAT("bat"),
    SKELETON("skeleton"),
    PLAYER("player_simple"),
    PLAYER_HELMET("player_helmet"),
    PLAYER_SWORD("player_sword"),
    PLAYER_SWORD_AND_HELMET("player_helmet_and_sword"),
    QUIT("quit"),
    REPEAT("repeat");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
