package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.MovableMonster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.model.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;

    private final Cell[][] cells;
    private final List<Cell> torches;

    private Player player;

    private final List<MovableMonster> movableMonsters;

    private final String level;

    public int getSkeletonCount() {
        int skeletonCount = 0;
        for (Monster monster : movableMonsters) {
            if (monster instanceof Skeleton) {
                skeletonCount += 1;
            }
        }
        return skeletonCount;
    }


    public GameMap(int width, int height, CellType defaultCellType, String level) {
        this.width = width;
        this.height = height;
        this.level = level;
        movableMonsters = new ArrayList<>();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        this.torches = new ArrayList<>();
    }

    public GameMap(GameState gameState, Player player, GameMap newMap) {
        this.level = gameState.getMapLevel();
        this.height = parseMap(gameState.getCurrentMap()).length;
        this.width = parseMap(gameState.getCurrentMap())[0].length();
        this.cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(newMap, x, y, CellType.EMPTY);
            }
        }
        movableMonsters = new ArrayList<>();
        this.torches = new ArrayList<>();
        this.player = player;
    }

    public String[] parseMap(String string) {
        return string.split("\n");
    }

    public void addTorch(Cell cell) {
        this.torches.add(cell);
    }

    public List<Cell> getTorches() {
        return torches;
    }

    public String getLevel() {
        return level;
    }

    public Cell getCell(int x, int y) {
        Cell cell = null;
        try {
            return cells[x][y];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Actor can't leave the map!");
        }
        return cell;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void addInventory(boolean isLoading) {
        Cell currentTile = getPlayer().getCell();
        if (currentTile.getItem() != null) {
            getPlayer().addItemToInventory(currentTile.getItem(), isLoading);
            currentTile.setType(CellType.FLOOR);
            currentTile.setItem(null);
        }
    }

    public List<MovableMonster> getMovableMonsters() {
        return movableMonsters;
    }

    public void addMonsterToMovableMonsters(MovableMonster monster) {
        movableMonsters.add(monster);
    }

    public void removeMonsterFromMovableMonsters(MovableMonster monster) {
        movableMonsters.remove(monster);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int column = 0; column < height; column++) {
            for (int row = 0; row < width; row++) {
                Cell cell = cells[row][column];
                if (cell.getActor() != null) {
                    builder.append(cell.getActor().getTileCharacter());
                } else if (cell.getItem() != null) {
                    builder.append(cell.getItem().getTileCharacter());
                } else {
                    builder.append(cell.getType().getCharacter());
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean isThisTheLastLevel() {
        return level.equals(Level.MAP_4.getMapLevel());
    }
}
