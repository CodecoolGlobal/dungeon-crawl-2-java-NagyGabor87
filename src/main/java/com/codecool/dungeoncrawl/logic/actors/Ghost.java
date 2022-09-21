package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ghost extends Monster {

    private int waitBeforeMove = 2;

    public Ghost(Cell cell) {
        super(cell);
        this.health = 15;
        this.damage = 3;
    }

    @Override
    public String getTileName() {
        return CellType.GHOST.getTileName();
    }

    @Override
    public int generateNextStepX() {
        int ghostX = this.getX();
        int playerX = cell.getGameMap().getPlayer().getX();
        int differenceX = playerX - ghostX;

        return moveTowardsPlayer(differenceX);
    }

    @Override
    public int generateNextStepY() {
        int ghostY = this.getY();
        int playerY = cell.getGameMap().getPlayer().getY();
        int differenceY = playerY - ghostY;

        return moveTowardsPlayer(differenceY);
    }

    @Override
    public void move(int dx, int dy) {
        if (waitBeforeMove == 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);

            // Prevent actor to leave map
            if (nextCell == null) {
                return;
            }
            // Prevent collision to other actors, can go through walls
            if (nextCell.getActor() != null) return;

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            waitBeforeMove = 2;
        } else {
            waitBeforeMove--;
        }
    }

    @Override
    protected void makeNoise() {
        // Doesn't make noise
    }

    private int moveTowardsPlayer(int difference){
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
