package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty", ' '),
    FLOOR("floor", '.'),
    WALL("wall", '#'),
    DISAPPEARING_WALL("disappearing_wall", '|'),
    SWORD("sword", 'K'),
    SPIDERWEB("spiderweb", 'w'),
    TORCH_A("torch_a", 't'),
    TORCH_B("torch_b", 'r'),
    HELMET("helmet", 'h'),
    KEY("key", 'y'),
    CLOSED_DOOR("closed_door", 'c'),
    OPEN_DOOR("open_door", 'd'),
    GHOST("ghost", 'g'),
    BAT("bat", 'b'),
    SKELETON("skeleton", 's'),
    PLAYER("player_simple", '@'),
    PLAYER_HELMET("player_helmet", '@'),
    PLAYER_SWORD("player_sword", '@'),
    PLAYER_SWORD_AND_HELMET("player_helmet_and_sword", '@'),
    POTION ("potion", 'p'),
    STRONGEST_POTION("strongest_potion", 'o'),
    POTION_SELLER ("potion_seller", 'S'),
    QUIT("quit", 'Q'),
    U("letter_U", 'U'),
    I("letter_I", 'I'),
    T("letter_T", 'T'),
    PLAY("play", 'P'),
    L("letter_L", 'L'),
    A("letter_A", 'A'),
    Y("letter_Y", 'Y'),
    ARROW("arrow", 'a');


    private final String tileName;
    private final char character;

    CellType(String tileName, char character) {
        this.tileName = tileName;
        this.character = character;
    }

    public String getTileName() {
        return tileName;
    }

    public char getCharacter() {
        return character;
    }
}
