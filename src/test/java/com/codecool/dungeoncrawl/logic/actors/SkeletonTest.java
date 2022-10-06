package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Level;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeletonTest {
    Cell cellOfSkeleton;
    GameMap testMap;
    Skeleton skeleton;

    @BeforeEach
    public void init() {
        cellOfSkeleton = new Cell(0, 0);
        testMap = MapLoader.loadMap(Level.MAP_1.getMapLevel(), null);
        skeleton = new Skeleton(cellOfSkeleton);
    }

    @Test
    void getTileNameValidTileNameTest() {
        String tileName = "skeleton";
        assertEquals(tileName, skeleton.getTileName());
    }

    @Test
    void getTileNameInvalidTileNameTest() {
        String tileName = "bat";
        assertNotEquals(tileName, skeleton.getTileName());
    }

    @Test
    void getTileCharacterValidCharacterTest() {
        char tileCharacter = 's';
        assertEquals(tileCharacter, skeleton.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest() {
        char tileName = 'y';
        assertNotEquals(tileName, skeleton.getTileCharacter());
    }

    @Test
    void generateNextStepXNotBiggerThanOne() {
        cellOfSkeleton.setGameMap(testMap);
        assertNotEquals(2, skeleton.generateNextStepX());
    }

    @Test
    void generateNextStepYNotBiggerThanOne() {
        cellOfSkeleton.setGameMap(testMap);
        assertNotEquals(2, skeleton.generateNextStepY());
    }


    @Test
    void moveToValidPositionTest() {
        cellOfSkeleton = new Cell(0, 0);
        cellOfSkeleton.setGameMap(testMap);
        skeleton = new Skeleton(cellOfSkeleton);
        skeleton.move(1, 1);
        assertEquals(1, skeleton.getCell().getX());
        assertEquals(1, skeleton.getCell().getY());
    }

    @Test
    void notMovingToInvalidPositionTest() {
        cellOfSkeleton = new Cell(0, 0);
        cellOfSkeleton.setGameMap(testMap);
        skeleton = new Skeleton(cellOfSkeleton);
        skeleton.move(-1, -1);
        assertEquals(0, skeleton.getCell().getX());
        assertEquals(0, skeleton.getCell().getY());
    }

    @Test
    void testGetTileNameForDeadSkeleton() {
        skeleton.setHealth(0);
        assertEquals("floor", skeleton.getTileName());
    }
}
