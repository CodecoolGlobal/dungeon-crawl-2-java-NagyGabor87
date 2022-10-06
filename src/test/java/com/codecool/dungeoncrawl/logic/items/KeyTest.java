package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    static Cell cell;
    Key key;

    @BeforeAll
    static void init() {
        cell = new Cell(0,0);
    }

    @BeforeEach
    void keyInit(){key = new Key(cell);}

    @Test
    void testIsKeyNull() {
        Key key = null;
        assertNull(key);
    }

    @Test
    void testKeyCorrectTileName(){
        assertEquals("key", key.getTileName());
    }

    @Test
    void testKeyWrongTileName(){
        assertNotEquals("item", key.getTileName());
    }

    @Test
    void testGetCorrectTileCharacter(){
        assertEquals('y', key.getTileCharacter());
    }

    @Test
    void testGetWrongTileCharacter(){
        assertNotEquals('x', key.getTileCharacter());
    }

}