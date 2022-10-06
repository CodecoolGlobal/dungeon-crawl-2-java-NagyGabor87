package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrongestPotionTest {
    static Cell cell;
    StrongestPotion strongestPotion;

    @BeforeAll
    static void init() {
        cell = new Cell(0,0);
    }

    @BeforeEach
    void potionInit(){
        strongestPotion = new StrongestPotion(cell);}

    @Test
    void testIsPotionNull() {
        StrongestPotion strongestPotion1 = null;
        assertNull(strongestPotion1);
    }

    @Test
    void testPotionCorrectTileName(){
        assertEquals("strongest_potion", strongestPotion.getTileName());
    }

    @Test
    void testPotionWrongTileName(){
        assertNotEquals("item", strongestPotion.getTileName());
    }

    @Test
    void testGetCorrectTileCharacter(){
        assertEquals('o', strongestPotion.getTileCharacter());
    }

    @Test
    void testGetWrongTileCharacter(){
        assertNotEquals('x', strongestPotion.getTileCharacter());
    }

    @Test
    void testGetHealingCorrect(){
        assertEquals(100, strongestPotion.getHealing());
    }

    @Test
    void testGetHealingWrong(){
        assertNotEquals(0, strongestPotion.getHealing());
    }

}