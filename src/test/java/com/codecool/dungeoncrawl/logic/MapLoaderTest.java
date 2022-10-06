package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {
    GameMap map;

    @BeforeEach
    void init() {map = new GameMap(3, 3, CellType.FLOOR, "map");}


    @Test
    void testCreateMapFromText() {
        String mapString = "#############                                  \n" +
                "#...........#    ###########################   \n" +
                "#.......s...######........................t#   \n" +
                "#.....................g....................c   \n" +
                "#...........######........................t#   \n" +
                "#.....s.....#    #..............############   \n" +
                "#.b........w#    #..b.....p.....#              \n" +
                "#...........#    #..............#              \n" +
                "#####..######    ###..###########              \n" +
                "    #..#           #..#                        \n" +
                "    #..#           #..#                        \n" +
                "    #..#         ###..######################   \n" +
                "  ###..####      #...........s.............#   \n" +
                "  #..@....#      #...........w.............#   \n" +
                "  #.......#      #......########...........#   \n" +
                "  #||.h...#      #......#      #....s......#   \n" +
                "  #y|...K.#      #......#      #...........#   \n" +
                "  #||.....#      #......########...........#   \n" +
                "  #..b....#      #..........b..............#   \n" +
                "  #########      ###########################   \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n" +
                "                                               \n";
        Player player = new Player(new Cell(0,0));
        GameMap newMap = MapLoader.loadMap(Level.MAP_1.getMapLevel(), player);
        assertEquals(mapString, newMap.toString());
    }

    @Test
    void testCreateMapFromModelWithPlayer() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        Player player = new Player(new Cell(0,0));
        GameMap newMap = MapLoader.loadMap(gameState, player);
        assertEquals("...\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelWithoutPlayer() {
        map.getCell(0,0).setType(CellType.PLAYER);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("@..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelOpenDoor() {
        map.getCell(0,0).setType(CellType.OPEN_DOOR);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("d..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelQuit() {
        map.getCell(0,0).setType(CellType.QUIT);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("Q..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterU() {
        map.getCell(0,0).setType(CellType.U);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("U..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterI() {
        map.getCell(0,0).setType(CellType.I);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("I..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterT() {
        map.getCell(0,0).setType(CellType.T);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("T..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelPlay() {
        map.getCell(0,0).setType(CellType.PLAY);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("P..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterL() {
        map.getCell(0,0).setType(CellType.L);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("L..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterA() {
        map.getCell(0,0).setType(CellType.A);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("A..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelLetterY() {
        map.getCell(0,0).setType(CellType.Y);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("Y..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelArrow() {
        map.getCell(0,0).setType(CellType.ARROW);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("a..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelPotionSeller() {
        map.getCell(0,0).setType(CellType.POTION_SELLER);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("S..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelTorchB() {
        map.getCell(0,0).setType(CellType.TORCH_B);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("r..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromModelStrongestPotion() {
        map.getCell(0,0).setType(CellType.STRONGEST_POTION);
        GameState gameState = new GameState(map.toString(), map.getLevel());
        GameMap newMap = MapLoader.loadMap(gameState, null);
        assertEquals("o..\n" +
                "...\n" +
                "...\n", newMap.toString());
    }

    @Test
    void testCreateMapFromTextWithInvalidCharacter() {
        Player player = new Player(new Cell(0,0));
        assertThrows(RuntimeException.class, () -> MapLoader.loadMap("/map_files/test_map_with_invalid_characters.txt", player));
    }
}