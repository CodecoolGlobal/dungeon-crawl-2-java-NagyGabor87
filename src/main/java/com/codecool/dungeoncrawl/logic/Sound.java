package com.codecool.dungeoncrawl.logic;

public enum Sound {
    BASIC_ATTACK("//home//g-sarkadi//Desktop//basic_attack.wav"),
    ADVANCED_ATTACK("//home//g-sarkadi//Desktop//advanced_attack.wav"),
    DOOR("//home//g-sarkadi//Desktop//door.wav"),
    MOVE("//home//g-sarkadi//Desktop//move.wav"),
    PICK_UP_ITEM("//home//g-sarkadi//Desktop//pickup.wav"),
    SKELETON_SOUND("//home//g-sarkadi//Desktop//skeleton.wav"),
    DEATH("//home//g-sarkadi//Desktop//death.wav");

    private final String filePath;

    Sound(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
