package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;

    private Player player;
    private int skeletonCount = 0;
    private String level;

    public int getSkeletonCount() {
        return skeletonCount;
    }

    public void setSkeletonCount(int monsterCount) {
        this.skeletonCount += monsterCount;
    }

    public GameMap(int width, int height, CellType defaultCellType, String level) {
        this.width = width;
        this.height = height;
        this.level = level;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
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

    public void addInventory() {
        Cell currentTile = getPlayer().getCell();
        if (currentTile.getItem() != null) {
            getPlayer().addItemToInventory(currentTile.getItem());
            currentTile.setType(CellType.FLOOR);
            currentTile.setItem(null);
        }
    }
}
