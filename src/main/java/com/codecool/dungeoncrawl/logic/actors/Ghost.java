package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

public class Ghost extends MovableMonster {

    private int waitThisManyRoundsBeforeMove = 2;

    public Ghost(Cell cell) {
        super(cell);
        this.health = 15 + RandomGenerator.generateRandomHealthModifier();
        this.damage = 6 + RandomGenerator.generateRandomDamageModifier();
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
        if (waitThisManyRoundsBeforeMove == 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);

            // Prevent actor to leave map
            if (nextCell == null) {
                return;
            }
            // Prevent collision to other actors, can go through walls
            if (nextCell.getActor() != null) return;

            cell.setActor(null);
            nextCell.setActor(this);
            this.cell = nextCell;
            waitThisManyRoundsBeforeMove = 2;
        } else {
            waitThisManyRoundsBeforeMove--;
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
