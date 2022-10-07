package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    GameMap map;
    String mapString;
    Date date;
    PlayerModel playerModel;

    String mapLevel;
    GameState gameState;

    @BeforeEach
    void init() {
        map = new GameMap(3, 3, CellType.FLOOR, "map");
        mapString = map.toString();
        date = new java.sql.Date(System.currentTimeMillis());
        playerModel = new PlayerModel(new Player(new Cell(0,0)));
        mapLevel = map.getLevel();
        gameState = new GameState(mapString, date, playerModel, mapLevel);
    }

    @Test
    void testGameStateFromMapString() {
        GameState gameState = new GameState(map.toString(), map.getLevel());
        assertEquals("currentMap:...\n" +
                "...\n" +
                "...\n" +
                ",discoveredMaps:[],mapLevel:map,stateId:0,", gameState.toString());
    }

    @Test
    void testGameStateFromRawData() {
        assertEquals("savedAt:" + java.time.LocalDate.now() +",currentMap:...\n" +
                "...\n" +
                "...\n" +
                ",discoveredMaps:[],player:hp:50,x:0,y:0,inventory:,playerId:0,,mapLevel:map,stateId:0,", gameState.toString());
    }

    @Test
    void testGameState_getDate() {
        assertEquals(date, gameState.getSavedAt());
    }

    @Test
    void testGameState_setDate() {
        Date newDate = new java.sql.Date(System.currentTimeMillis());
        gameState.setSavedAt(newDate);
        assertEquals(newDate, gameState.getSavedAt());
    }

    @Test
    void testGameState_getCurrentMap() {
        assertEquals(mapString, gameState.getCurrentMap());
    }

    @Test
    void testGameState_setCurrentMap() {
        GameMap newMap = new GameMap(5, 5, CellType.WALL, "different_map");
        gameState.setCurrentMap(newMap.toString());
        assertEquals(newMap.toString(), gameState.getCurrentMap());
    }

    @Test
    void testGameState_getDiscoveredMap() {
        assertEquals(new ArrayList<>(), gameState.getDiscoveredMaps());
    }

    @Test
    void testGameState_setDiscoveredMap() {
        List<String> newDiscoveredMaps = new ArrayList<>();
        String dummyMap = "dummy_text";
        newDiscoveredMaps.add(dummyMap);
        gameState.addDiscoveredMap(dummyMap);
        assertEquals(newDiscoveredMaps, gameState.getDiscoveredMaps());
    }

    @Test
    void testGameState_getId() {
        assertEquals(0, gameState.getId());
    }

    @Test
    void testGameState_setId() {
        gameState.setId(99);
        assertEquals(99, gameState.getId());
    }

    @Test
    void testGameState_getPlayer() {
        assertEquals(playerModel, gameState.getPlayer());
    }

    @Test
    void testGameState_setPlayer() {
        Player differentPlayer = new Player(new Cell(1,1));
        PlayerModel differentModel = new PlayerModel(differentPlayer);
        gameState.setPlayer(differentModel);
        assertEquals(differentModel, gameState.getPlayer());
    }

    @Test
    void testGameState_getMapLevel() {
        assertEquals(mapLevel, gameState.getMapLevel());
    }

    @Test
    void testGameState_setMapLevel() {
        String dummyMapLevel = "dummy_map_level";
        gameState.setMapLevel(dummyMapLevel);
        assertEquals(dummyMapLevel, gameState.getMapLevel());
    }
}