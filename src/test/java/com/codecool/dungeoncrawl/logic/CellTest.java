package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap map = new GameMap(3, 3, CellType.FLOOR, "map");
    Cell cell;
    @BeforeEach
    void init() {cell = map.getCell(1,1);}

    @Test
    void getNeighbor() {
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell = map.getCell(1, 0);
        assertNull(cell.getNeighbor(0, -1));

        cell = map.getCell(1, 2);
        assertNull(cell.getNeighbor(0, 1));
    }

    @Test
    void testGetCellType() {
        assertEquals(CellType.FLOOR, cell.getType());
    }

    @Test
    void testSetType() {
        cell.setType(CellType.WALL);
        assertEquals(CellType.WALL, cell.getType());
    }

    @Test
    void testGetActor() {
        Actor skeleton = new Skeleton(cell);
        assertEquals(skeleton, cell.getActor());
    }

    @Test
    void testSetActor() {
        Actor skeleton = new Skeleton(new Cell(0,0));
        cell.setActor(skeleton);
        assertEquals(skeleton, cell.getActor());
    }

    @Test
    void testRemoveActors(){
        Actor skeleton = new Skeleton(new Cell(0,0));
        cell.setActor(skeleton);
        cell.removeActor();
        assertNull(cell.getActor());
    }

    @Test
    void testGetTileName() {
        assertEquals("floor", cell.getTileName());
    }

    @Test
    void testGetTileCharacter() {
        assertEquals('.', cell.getTileCharacter());
    }

    @Test
    void testGetX(){
        assertEquals(1, cell.getX());
    }

    @Test
    void testGetY(){
        assertEquals(1, cell.getY());
    }

    @Test
    void testGetMap(){
        assertEquals(map, cell.getGameMap());
    }

    @Test
    void testSetGameMap() {
        GameMap newMap = new GameMap(5,5, CellType.WALL, "level");
        cell.setGameMap(newMap);
        assertEquals(newMap, cell.getGameMap());
    }

    @Test
    void testGetItem() {
        Item sword = new Sword(cell);
        assertEquals(sword, cell.getItem());
    }

    @Test
    void testSetItem() {
        Item sword = new Sword(new Cell(0,0));
        cell.setItem(sword);
        assertEquals(sword, cell.getItem());
    }
}