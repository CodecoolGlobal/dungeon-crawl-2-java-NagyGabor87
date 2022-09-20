package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Monster {


    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public int generateNextStepX() {
        int ghostX = this.getX();
        int playerX = cell.getGameMap().getPlayer().getX();
        int differenceX = playerX - ghostX;
        System.out.println(differenceX);

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
        Cell nextCell = cell.getNeighbor(dx, dy);

        // Prevent actor to leave map
        if (nextCell == null) {
            return;
        }
        // Prevent collision to other actors
        if ( nextCell.getActor() instanceof Actor) return;

        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
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
