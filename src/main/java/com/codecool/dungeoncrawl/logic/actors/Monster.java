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

        if ((nextCell.getType() == CellType.WALL) || (nextCell.getType() == CellType.CLOSED_DOOR) || nextCell.getActor() != null) return;

        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
        makeNoise();
    }

    protected abstract void makeNoise();

    public abstract int generateNextStepX();
    public abstract int generateNextStepY();
}
