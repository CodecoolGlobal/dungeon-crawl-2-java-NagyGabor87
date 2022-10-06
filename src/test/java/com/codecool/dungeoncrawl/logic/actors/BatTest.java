package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatTest {
    Cell cell = new Cell(0, 0);
    Bat bat = new Bat(cell);

    @Test
    void getTileNameValidTileNameTest() {
        String tileName = "bat";
        assertEquals(tileName, bat.getTileName());
    }
    @Test
    void getTileNameInvalidTileNameTest() {
        String tileName = "ghost";
        assertNotEquals(tileName, bat.getTileName());
    }

    @Test
    void getTileCharacterValidCharacterTest() {
        char tileName = 'b';
        assertEquals(tileName, bat.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest() {
        char tileName = 'y';
        assertNotEquals(tileName, bat.getTileCharacter());
    }

    @Test
    void generateNextStepXTest() {
        int zeroInt = 0;
        assertEquals(zeroInt, bat.generateNextStepX());
    }

    @Test
    void generateNextStepYTest() {
        int zeroInt = 0;
        assertEquals(zeroInt, bat.generateNextStepY());
    }
}
