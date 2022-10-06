package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Helmet;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Cell cellOfPlayer1 = new Cell(0, 0);
    GameMap testMap = MapLoader.loadMap(LEVEL.MAP_1.getMapLevel(), null);
    Player player1 = new Player(cellOfPlayer1);
    PlayerModel playerModel = new PlayerModel("player2", 10, 10, "sword,key,helmet", 50, 2);
    Player player2 = new Player(playerModel);
    Cell cellOfPlayer2 = player2.getCell();

    @Test
    void getTileNameValidTileNameTest() {
        String tileName = "player_simple";
        assertEquals(tileName, player1.getTileName());
    }

    @Test
    void getTileNameValidTileNameTest2() {
        String tileName = "player_helmet_and_sword";
        assertEquals(tileName, player2.getTileName());
    }

    @Test
    void getTileNameInvalidTileNameTest() {
        String tileName = "bat";
        assertNotEquals(tileName, player1.getTileName());
    }

    @Test
    void getTileNameInvalidTileNameTest2() {
        String tileName = "bat";
        assertNotEquals(tileName, player2.getTileName());
    }

    @Test
    void getTileCharacterValidCharacterTest() {
        char tileCharacter = '@';
        assertEquals(tileCharacter, player1.getTileCharacter());
    }

    @Test
    void getTileCharacterValidCharacterTest2() {
        char tileCharacter = '@';
        assertEquals(tileCharacter, player2.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest() {
        char tileName = 'y';
        assertNotEquals(tileName, player1.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest2() {
        char tileName = 'y';
        assertNotEquals(tileName, player2.getTileCharacter());
    }


    @Test
    void moveToValidPositionTest() {
        cellOfPlayer1.setGameMap(testMap);
        player1 = new Player(cellOfPlayer1);
        player1.move(1, 1);
        assertEquals(1, player1.getCell().getX());
        assertEquals(1, player1.getCell().getY());
    }

    @Test
    void moveToValidPositionTest2() {
        cellOfPlayer2.setGameMap(testMap);
        player2 = new Player(cellOfPlayer2);
        player2.move(1, 1);
        assertEquals(11, player2.getCell().getX());
        assertEquals(11, player2.getCell().getY());
    }

    @Test
    void notMovingToInvalidPositionTest() {
        cellOfPlayer1.setGameMap(testMap);
        player1 = new Player(cellOfPlayer1);
        player1.move(-1, -1);
        assertEquals(0, player1.getCell().getX());
        assertEquals(0, player1.getCell().getY());
    }

    @Test
    void notMovingToInvalidPositionTest2() {
        cellOfPlayer2.setGameMap(testMap);
        player2 = new Player(cellOfPlayer2);
        player2.move(-11, -11);
        assertEquals(10, player2.getCell().getX());
        assertEquals(10, player2.getCell().getY());
    }

    @Test
    void getInventory() {
        Cell cellOfSword = new Cell(0, 0);
        Cell cellOfHelmet = new Cell(0, 5);
        Sword sword = new Sword(cellOfSword);
        Helmet helmet = new Helmet(cellOfHelmet);
        player1.addItemToInventory(sword, true);
        player1.addItemToInventory(helmet, true);
        assertEquals("sword,helmet", player1.getInventory());
    }

    @Test
    void getInventory2() {
        assertEquals("sword,key,helmet", player2.getInventory());
    }

    @Test
    void inventoryToString() {
        Cell cellOfSword = new Cell(0, 0);
        Cell cellOfHelmet = new Cell(0, 5);
        Sword sword = new Sword(cellOfSword);
        Helmet helmet = new Helmet(cellOfHelmet);
        player1.addItemToInventory(sword, true);
        player1.addItemToInventory(helmet, true);
        assertEquals("- sword\n- helmet\n", player1.inventoryToString());
    }

    @Test
    void inventoryToString2() {
        assertEquals("- sword\n- key\n- helmet\n", player2.inventoryToString());
    }

    @Test
    void hasAdvancedWeaponTest() {
        Cell cellOfSword = new Cell(0, 0);
        Sword sword = new Sword(cellOfSword);
        player1.addItemToInventory(sword, true);
        assertTrue(player1.hasAdvancedWeapon());
    }

    @Test
    void hasAdvancedWeaponTest2() {
        assertTrue(player2.hasAdvancedWeapon());
    }

    @Test
    void returnKeyTest() {
        Cell cellOfKey = new Cell(0, 0);
        Key key = new Key(cellOfKey);
        player1.addItemToInventory(key, true);
        assertEquals("key", player1.returnKey().getTileName());
    }

    @Test
    void returnKeyTest2() {
        assertEquals("key", player2.returnKey().getTileName());
    }

    @Test
    void removeItemTest() {
        Cell cellOfKey = new Cell(0, 0);
        Key key = new Key(cellOfKey);
        player1.addItemToInventory(key, true);
        assertEquals("key", player1.returnKey().getTileName());
        player1.removeItem(key);
        assertEquals("", player1.getInventory());
    }

    @Test
    void removeItemTest2() {
        Cell cellOfKey = new Cell(0, 0);
        Key key = new Key(cellOfKey);
        player2.addItemToInventory(key, true);
        assertEquals("key", player2.returnKey().getTileName());
        player2.removeItem(key);
        assertEquals("sword,key,helmet", player2.getInventory());
    }

    @Test
    void resetPlayerTest() {
        player1.setHealth(10);
        assertEquals(10, player1.getHealth());
        player1.resetPlayer();
        assertEquals(50, player1.getHealth());
    }

    @Test
    void resetPlayerTest2() {
        player2.setHealth(10);
        assertEquals(10, player2.getHealth());
        player2.resetPlayer();
        assertEquals(50, player2.getHealth());
    }

    @Test
    void getIdTest() {
        player1.setId(10);
        assertEquals(10, player1.getId());
    }

    @Test
    void getIdTest2() {
        player2.setId(20);
        assertEquals(20, player2.getId());
    }

}
