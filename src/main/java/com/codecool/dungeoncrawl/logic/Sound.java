package com.codecool.dungeoncrawl.logic;

public enum Sound {
    ATTACK("//home//g-sarkadi//Desktop//swing3.wav"),
    DOOR("//home//g-sarkadi//Desktop//door.wav"),
    MOVE("//home//g-sarkadi//Desktop//random5.wav"),
    PICK_UP_ITEM("//home//g-sarkadi//Desktop//metal-ringing.wav");

    private final String filePath;

    Sound(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
