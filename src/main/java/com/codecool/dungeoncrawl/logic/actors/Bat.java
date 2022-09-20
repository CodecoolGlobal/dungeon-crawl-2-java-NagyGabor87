package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Monster {


    public Bat(Cell cell) {
        super(cell);
        this.health = 10;
        this.damage = 2;
    }

    @Override
    public String getTileName() {
        return "bat";
    }

    // Bat is stationary
    @Override
    public int generateNextStepX() {
        return 0;
    }

    @Override
    public int generateNextStepY() {
        return 0;
    }
}
