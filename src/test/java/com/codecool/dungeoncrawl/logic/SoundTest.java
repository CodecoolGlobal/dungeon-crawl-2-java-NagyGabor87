package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundTest {
    @Test
    void testGetFilePathSkeleton() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "skeleton.wav";
        assertEquals(currentFilePath, Sound.SKELETON_SOUND.getFilePath());
    }

    @Test
    void testGetFilePathPickup() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "pickup.wav";
        assertEquals(currentFilePath, Sound.PICK_UP_ITEM.getFilePath());
    }

    @Test
    void testGetFilePathMove() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "move.wav";
        assertEquals(currentFilePath, Sound.MOVE.getFilePath());
    }

    @Test
    void testGetFilePathDoor() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "door.wav";
        assertEquals(currentFilePath, Sound.DOOR.getFilePath());
    }

    @Test
    void testGetFilePathAdvancedAttack() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "advanced_attack.wav";
        assertEquals(currentFilePath, Sound.ADVANCED_ATTACK.getFilePath());
    }

    @Test
    void testGetFilePathBasicAttack() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "basic_attack.wav";
        assertEquals(currentFilePath, Sound.BASIC_ATTACK.getFilePath());
    }

    @Test
    void testGetFilePathDeath() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "death.wav";
        assertEquals(currentFilePath, Sound.DEATH.getFilePath());
    }

    @Test
    void testGetFilePathPotion() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "bubble.wav";
        assertEquals(currentFilePath, Sound.POTION.getFilePath());
    }

    @Test
    void testGetFilePathMusic() {
        String currentFilePath = System.getProperty("user.dir") + "//src//main//resources//sound_files//" + "music.wav";
        assertEquals(currentFilePath, Sound.MUSIC.getFilePath());
    }
}