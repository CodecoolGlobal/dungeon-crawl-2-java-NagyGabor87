package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Level;
import com.codecool.dungeoncrawl.logic.MapLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PotionSellerTest {
    Cell cellOfPotionSeller = new Cell(3, 12);
    GameMap testMap = MapLoader.loadMap(Level.MAP_1.getMapLevel(), null);
    PotionSeller potionSeller = new PotionSeller(cellOfPotionSeller);

    @Test
    void getTileNameValidTileNameTest() {
        String tileName = "potion_seller";
        assertEquals(tileName, potionSeller.getTileName());
    }

    @Test
    void getTileNameInvalidTileNameTest() {
        String tileName = "bat";
        assertNotEquals(tileName, potionSeller.getTileName());
    }

    @Test
    void getTileCharacterValidCharacterTest() {
        char tileCharacter = 'S';
        assertEquals(tileCharacter, potionSeller.getTileCharacter());
    }

    @Test
    void getTileCharacterInvalidCharacterTest() {
        char tileName = 'y';
        assertNotEquals(tileName, potionSeller.getTileCharacter());
    }

    @Test
    void givePotionNeighborCellIsFloorTest() {
        potionSeller.getCell().setGameMap(testMap);
        potionSeller.givePotion();
        assertEquals("strongest_potion", cellOfPotionSeller.getNeighbor(0,1).getItem().getTileName());
    }

    @Test
    void givePotionNeighborCellIsNotFloorTest() {
        cellOfPotionSeller = new Cell(16, 4);
        PotionSeller potionSeller = new PotionSeller(cellOfPotionSeller);
        potionSeller.getCell().setGameMap(testMap);
        potionSeller.givePotion();
        assertNull(cellOfPotionSeller.getNeighbor(0, 1).getItem());
    }

}
