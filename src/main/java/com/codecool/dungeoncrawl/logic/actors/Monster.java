package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public abstract class Monster extends Actor {

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        // Prevent actor to leave map
        if (nextCell == null) {
            return;
        }

        // Prevent actor to collide wall or other actors
        if (isNextCellOccupied(nextCell)) return;

        cell.setActor(null);
        nextCell.setActor(this);
        this.cell = nextCell;
        makeNoise();
    }

    private static boolean isNextCellOccupied(Cell nextCell) {
        return (nextCell.getType() == CellType.WALL) ||
                nextCell.getType() == CellType.DISAPPEARING_WALL ||
                nextCell.getType() == CellType.CLOSED_DOOR ||
                nextCell.getType() == CellType.POTION_SELLER ||
                nextCell.getActor() != null;
    }

    protected abstract void makeNoise();

    public abstract int generateNextStepX();
    public abstract int generateNextStepY();
}
