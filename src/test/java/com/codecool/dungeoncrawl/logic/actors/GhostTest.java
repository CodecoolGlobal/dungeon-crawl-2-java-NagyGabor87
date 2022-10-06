package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Level;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {
    Cell cellOfGhost = new Cell(0, 0);
    GameMap testMap = MapLoader.loadMap(Level.MAP_1.getMapLevel(), null);
    Ghost ghost = new Ghost(cellOfGhost);

    @Test
    void getTileNameValidTileNameTest() {
        String tileName = "ghost";
        assertEquals(tileName, ghost.getTileName());
    }

    @Test
    void getTileNameInvalidTileNameTest() {
        String tileName = "bat";
        assertNotEquals(tileName, ghost.getTileName());
    }

    @Test
    void getTileCharacterValidCharacterTest() {
        char tileCharacter = 'g';
        assertEquals(tileCharacter, ghost.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest() {
        char tileName = 'y';
        assertNotEquals(tileName, ghost.getTileCharacter());
    }

    @Test
    void generateNextStepXWithBiggerPlayerXTest() {
        cellOfGhost.setGameMap(testMap);
        assertEquals(1, ghost.generateNextStepX());
    }

    @Test
    void generateNextStepYWithBiggerPlayerYTest() {
        cellOfGhost.setGameMap(testMap);
        assertEquals(1, ghost.generateNextStepX());
    }

    @Test
    void generateNextStepXWithSmallerPlayerXTest() {
        cellOfGhost = new Cell(20, 20);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        assertEquals(-1, ghost.generateNextStepX());
    }

    @Test
    void generateNextStepYWithSmallerPlayerYTest() {
        cellOfGhost = new Cell(20, 20);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        assertEquals(-1, ghost.generateNextStepX());
    }

    @Test
    void generateNextStepXWithEqualPlayerXTest() {
        cellOfGhost = new Cell(5, 16);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        assertEquals(0, ghost.generateNextStepX());
    }

    @Test
    void generateNextStepYWithEqualPlayerYTest() {
        cellOfGhost = new Cell(5, 16);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        assertEquals(0, ghost.generateNextStepX());
    }

    @Test
    void moveToValidPositionTest() {
        cellOfGhost = new Cell(0, 0);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        ghost.move(1, 1);
        assertNull(ghost.getCell().getNeighbor(-1,-1));
        assertEquals("ghost", ghost.getCell().getActor().getTileName());
    }

    @Test
    void notMovingToInvalidPositionTest() {
        cellOfGhost = new Cell(0, 0);
        cellOfGhost.setGameMap(testMap);
        ghost = new Ghost(cellOfGhost);
        ghost.move(-1, -1);
        assertEquals(0, ghost.getCell().getX());
        assertEquals(0, ghost.getCell().getY());
    }


}
