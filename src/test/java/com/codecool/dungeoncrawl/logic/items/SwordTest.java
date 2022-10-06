package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    static Cell cell;
    Sword sword;

    @BeforeAll
    static void init() {
        cell = new Cell(0,0);
    }

    @BeforeEach
    void swordInit(){
        sword = new Sword(cell);}

    @Test
    void testIsSwordNull() {
        Sword sword = null;
        assertNull(sword);
    }

    @Test
    void testSwordCorrectTileName(){
        assertEquals("sword", sword.getTileName());
    }

    @Test
    void testSwordWrongTileName(){
        assertNotEquals("item", sword.getTileName());
    }

    @Test
    void testGetCorrectTileCharacter(){
        assertEquals('K', sword.getTileCharacter());
    }

    @Test
    void testGetWrongTileCharacter(){
        assertNotEquals('x', sword.getTileCharacter());
    }

    @Test
    void testGetDamageCorrect(){
        assertEquals(5, sword.getDamage());
    }

    @Test
    void testGetDamageWrong(){
        assertNotEquals(0, sword.getDamage());
    }
}