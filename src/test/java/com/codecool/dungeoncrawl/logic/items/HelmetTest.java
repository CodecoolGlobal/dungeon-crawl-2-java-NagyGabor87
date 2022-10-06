package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelmetTest {
    static Cell cell;
    Helmet helmet;

    @BeforeAll
    static void init() {
        cell = new Cell(0,0);
    }

    @BeforeEach
    void helmetInit(){helmet = new Helmet(cell);}

    @Test
    void testIsHelmetNull() {
        Helmet helmet = null;
        assertNull(helmet);
    }

    @Test
    void testHelmetCorrectTileName(){
        assertEquals("helmet", helmet.getTileName());
    }

    @Test
    void testHelmetWrongTileName(){
        assertNotEquals("item", helmet.getTileName());
    }

    @Test
    void testGetCorrectTileCharacter(){
        assertEquals('h', helmet.getTileCharacter());
    }

    @Test
    void testGetWrongTileCharacter(){
        assertNotEquals('x', helmet.getTileCharacter());
    }

    @Test
    void testGetArmorCorrect(){
        assertEquals(5, helmet.getArmor());
    }

    @Test
    void testGetArmorWrong(){
        assertNotEquals(0, helmet.getArmor());
    }
}