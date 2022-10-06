package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void testGetLevelCorrect() {
        assertEquals("/map_files/map_1.txt", Level.MAP_1.getMapLevel());
    }

    @Test
    void testGetLevelIncorrect() {
        assertNotEquals("incorrect_filepath", Level.MAP_1.getMapLevel());
    }

    @Test
    void testNextLevel1() {
        assertEquals(Level.MAP_2, Level.MAP_1.nextLevel());
    }

    @Test
    void testNextLevel2() {
        assertEquals(Level.MAP_3, Level.MAP_2.nextLevel());
    }

    @Test
    void testNextLevel3() {
        assertEquals(Level.MAP_4, Level.MAP_3.nextLevel());
    }

    @Test
    void testNextLevel4() {
        assertEquals(Level.MENU, Level.MAP_4.nextLevel());
    }

}