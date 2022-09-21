package com.codecool.dungeoncrawl.logic;

public enum Sound {
    BASIC_ATTACK("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//basic_attack.wav"),
    ADVANCED_ATTACK("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//advanced_attack.wav"),
    DOOR("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//door.wav"),
    MOVE("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//move.wav"),
    PICK_UP_ITEM("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//pickup.wav"),
    SKELETON_SOUND("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//skeleton.wav"),
    DEATH("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//death.wav"),
    MUSIC("//home//" + getUsername() + "//codecool_projects//03_oop//03_week_pair//dungeon-crawl-1-java-NagyGabor87//src//main//resources//music.wav");

    private final String filePath;

    Sound(String filePath) {
        this.filePath = filePath;

    }

    public String getFilePath() {
        return filePath;
    }

    private static String getUsername() {
        return System.getProperty("user.name");
    }
}
