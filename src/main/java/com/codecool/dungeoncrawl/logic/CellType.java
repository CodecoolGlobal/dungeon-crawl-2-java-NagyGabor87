package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.StrongestPotion;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
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
    POTION ("potion"),
    STRONGEST_POTION("strongest_potion"),
    POTION_SELLER ("potion_seller"),
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
