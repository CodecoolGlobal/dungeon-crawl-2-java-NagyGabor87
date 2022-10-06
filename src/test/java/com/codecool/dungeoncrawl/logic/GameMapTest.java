package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    GameMap map;

    @BeforeEach
    void init() {map = new GameMap(3, 3, CellType.FLOOR, "map");}

    @Test
    void testAddMonsterToMovableMonsters() {
        Skeleton skeleton1 = new Skeleton(new Cell(0,0));
        Skeleton skeleton2 = new Skeleton(new Cell(0,0));
        Ghost ghost = new Ghost(new Cell(0,0));
        map.addMonsterToMovableMonsters(skeleton1);
        map.addMonsterToMovableMonsters(skeleton2);
        map.addMonsterToMovableMonsters(ghost);
        assertEquals(3, map.getMovableMonsters().size());
    }

    @Test
    void testRemoveMonsterFromMovableMonsters() {
        Skeleton skeleton1 = new Skeleton(new Cell(0,0));
        Skeleton skeleton2 = new Skeleton(new Cell(0,0));
        map.addMonsterToMovableMonsters(skeleton1);
        map.addMonsterToMovableMonsters(skeleton2);
        map.removeMonsterFromMovableMonsters(skeleton2);
        assertEquals(1, map.getMovableMonsters().size());
    }

    @Test
    void testSkeletonCount(){
        Skeleton skeleton1 = new Skeleton(new Cell(0,0));
        Skeleton skeleton2 = new Skeleton(new Cell(0,0));
        Ghost ghost = new Ghost(new Cell(0,0));
        map.addMonsterToMovableMonsters(skeleton1);
        map.addMonsterToMovableMonsters(skeleton2);
        map.addMonsterToMovableMonsters(ghost);
        assertEquals(2, map.getSkeletonCount());
    }

    @Test
    void testAddTorch() {
        Cell cellA = new Cell(0,0);
        cellA.setType(CellType.TORCH_A);
        Cell cellB = new Cell(0,0);
        cellB.setType(CellType.TORCH_B);
        map.addTorch(cellA);
        map.addTorch(cellB);
        assertEquals(2, map.getTorches().size());
    }

    @Test
    void testGetLevel() {
        assertEquals("map", map.getLevel());
    }

    @Test
    void testGetCells() {
        assertEquals(3, map.getCells().length);
        assertEquals(3, map.getCells()[0].length);
    }

    @Test
    void testGetPlayerNull() {
        assertNull(map.getPlayer());
    }

    @Test
    void testSetPlayer() {
        Player player = new Player(new Cell(0,0));
        map.setPlayer(player);
        assertNotNull(map.getPlayer());
    }

    @Test
    void testGetPlayerValid() {
        Player player = new Player(new Cell(0,0));
        map.setPlayer(player);
        assertEquals(player, map.getPlayer());
    }

    @Test
    void testGetWidth() {
        assertEquals(3, map.getWidth());
    }

    @Test
    void testGetHeight() {
        assertEquals(3, map.getHeight());
    }

    @Test
    void testAddInventory() {
        Cell cell = map.getCell(0,0);
        Sword sword = new Sword(cell);
        cell.setItem(sword);
        Player player = new Player(cell);
        map.setPlayer(player);
        map.addInventory(true);
        assertEquals("sword", map.getPlayer().getInventory());
    }

    @Test
    void testToString() {
        Cell cell1 = map.getCell(0,0);
        Sword sword = new Sword(cell1);
        Cell cell2 = map.getCell(0,1);
        Skeleton skeleton = new Skeleton(cell2);
        assertEquals("K..\ns..\n...\n", map.toString());
    }

    @Test
    void testParseMap() {
        String incomingInventoryData = "sword\nkey\nhelmet";
        String[] expectedInventoryArray = {"sword", "key", "helmet"};
        assertTrue(Arrays.equals(expectedInventoryArray, map.parseMap(incomingInventoryData)));
    }

    @Test
    void testGameMapConstructorWithModel_testLevel() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        Player player = new Player(new Cell(0,0));
        GameMap newGameMap = new GameMap(gameState, player, map);
        assertEquals(map.getLevel(), newGameMap.getLevel());
    }

    @Test
    void testGameMapConstructorWithModel_testWidth() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        Player player = new Player(new Cell(0,0));
        GameMap newGameMap = new GameMap(gameState, player, map);
        assertEquals(map.getWidth(), newGameMap.getWidth());
    }

    @Test
    void testGameMapConstructorWithModel_testHeight() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        Player player = new Player(new Cell(0,0));
        GameMap newGameMap = new GameMap(gameState, player, map);
        assertEquals(map.getHeight(), newGameMap.getHeight());
    }

    @Test
    void testGameMapConstructorWithModel_testPlayer() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        Player player = new Player(new Cell(0,0));
        GameMap newGameMap = new GameMap(gameState, player, map);
        assertEquals(player, newGameMap.getPlayer());
    }
}