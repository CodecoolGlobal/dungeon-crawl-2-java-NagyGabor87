package com.codecool.dungeoncrawl.model;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerModelTest {
    Cell cell = new Cell(0, 0);
    Player player = new Player(cell);
    PlayerModel playerModel1 =new PlayerModel("player1", 10, 10, "sword", 50, 1);

    PlayerModel playerModel2 = new PlayerModel(player);

    @Test
    void getPlayerNameTest() {
        playerModel2.setPlayerName("player2");
        assertEquals("player1", playerModel1.getPlayerName());
        assertEquals("player2", playerModel2.getPlayerName());
    }

    @Test
    void setPlayerNameTest() {
        playerModel1.setPlayerName("player100");
        assertEquals("player100", playerModel1.getPlayerName());
    }

    @Test
    void getPlayerHpTest() {
        assertEquals(50, playerModel1.getHp());
        assertEquals(50, playerModel2.getHp());
    }

    @Test
    void setPlayerHpTest() {
        playerModel1.setHp(100);
        playerModel2.setHp(100);
        assertEquals(100, playerModel1.getHp());
        assertEquals(100, playerModel2.getHp());
    }

    @Test
    void getXTest() {
        assertEquals(10, playerModel1.getX());
        assertEquals(0, playerModel2.getX());
    }

    @Test
    void setXTest() {
        playerModel1.setX(20);
        playerModel2.setX(30);
        assertEquals(20, playerModel1.getX());
        assertEquals(30, playerModel2.getX());
    }

    @Test
    void getYTest() {
        assertEquals(10, playerModel1.getY());
        assertEquals(0, playerModel2.getY());
    }

    @Test
    void setYTest() {
        playerModel1.setY(15);
        playerModel2.setY(16);
        assertEquals(15, playerModel1.getY());
        assertEquals(16, playerModel2.getY());
    }

    @Test
    void getInventoryTest() {
        assertEquals("sword", playerModel1.getInventory());
        assertEquals("", playerModel2.getInventory());
    }

    @Test
    void addToInventoryTest() {
        playerModel1.addToInventory("key");
        playerModel2.addToInventory("key");
        assertEquals("key", playerModel1.getInventory());
        assertEquals("key", playerModel2.getInventory());
    }

    @Test
    void getIdTest() {
        assertEquals(1, playerModel1.getId());
        assertEquals(0, playerModel2.getId());
    }

    @Test
    void setIdTest() {
        playerModel1.setId(10);
        playerModel2.setId(15);
        assertEquals(10, playerModel1.getId());
        assertEquals(15, playerModel2.getId());
    }


}
