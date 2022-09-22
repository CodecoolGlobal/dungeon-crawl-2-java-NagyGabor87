package com.codecool.dungeoncrawl.logic;

public enum Sound {
    BASIC_ATTACK(getCurrentFilePath() + "basic_attack.wav"),
    ADVANCED_ATTACK(getCurrentFilePath() + "advanced_attack.wav"),
    DOOR(getCurrentFilePath() + "door.wav"),
    MOVE(getCurrentFilePath() + "move.wav"),
    PICK_UP_ITEM(getCurrentFilePath() + "pickup.wav"),
    SKELETON_SOUND(getCurrentFilePath() + "skeleton.wav"),
    DEATH(getCurrentFilePath() + "death.wav"),
    MUSIC(getCurrentFilePath() + "music.wav"),

    POTION(getCurrentFilePath() + "bubble.wav");

    private final String filePath;

    Sound(String filePath) {
        this.filePath = filePath;

    }

    public String getFilePath() {
        return filePath;
    }

    private static String getCurrentFilePath() {
        return System.getProperty("user.dir") + "//src//main//resources//sound_files//";
    }
}
