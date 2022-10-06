package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {
    static Cell cell;
    Potion potion;

    @BeforeAll
    static void init() {
        cell = new Cell(0,0);
    }

    @BeforeEach
    void potionInit(){
        potion = new Potion(cell);}

    @Test
    void testIsPotionNull() {
        Potion potion = null;
        assertNull(potion);
    }

    @Test
    void testPotionCorrectTileName(){
        assertEquals("potion", potion.getTileName());
    }

    @Test
    void testPotionWrongTileName(){
        assertNotEquals("item", potion.getTileName());
    }

    @Test
    void testGetCorrectTileCharacter(){
        assertEquals('p', potion.getTileCharacter());
    }

    @Test
    void testGetWrongTileCharacter(){
        assertNotEquals('x', potion.getTileCharacter());
    }

    @Test
    void testGetHealingCorrect(){
        assertEquals(25, potion.getHealing());
    }

    @Test
    void testGetHealingWrong(){
        assertNotEquals(0, potion.getHealing());
    }
}