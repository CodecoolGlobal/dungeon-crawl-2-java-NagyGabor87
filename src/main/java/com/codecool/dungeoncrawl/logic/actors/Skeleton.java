package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Skeleton extends Monster {

    private int health;
    public Skeleton(Cell cell) {
        super(cell);
        health = 15;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }


    @Override
    public String getTileName() {
        if (getHealth() <= 0) {
            return "floor";
        }
        return "skeleton";
    }
}
