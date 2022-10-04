package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private List<Cell> torches;

    private Player player;
    private int skeletonCount = 0;
    private String level;

    public int getSkeletonCount() {
        return skeletonCount;
    }

    public void setSkeletonCount(int newCount) {
        this.skeletonCount = newCount;
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
        this.torches = new ArrayList<>();
    }

    public void fetchTorches() {
        List<Cell> torches = new ArrayList<>();
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                if (cell.getType() == CellType.TORCH_A) {
                    torches.add(cell);
                }
            }
        }
        this.torches = torches;
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

    public Cell[][] getCells(){
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

    public void addInventory() {
        Cell currentTile = getPlayer().getCell();
        if (currentTile.getItem() != null) {
            getPlayer().addItemToInventory(currentTile.getItem());
            currentTile.setType(CellType.FLOOR);
            currentTile.setItem(null);
        }
    }
}
