package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeletonTest {
    Cell cellOfSkeleton = new Cell(0, 0);
    GameMap testMap = MapLoader.loadMap(LEVEL.MAP_1.getMapLevel(), null);
    Skeleton skeleton = new Skeleton(cellOfSkeleton);

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
        assertNotEquals(2, skeleton.generateNextStepX());
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

    //TODO isNextCellOccupied !!!!
}
