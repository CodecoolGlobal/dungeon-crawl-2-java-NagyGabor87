package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Monster extends Actor {

    public Monster(Cell cell) {
        super(cell);
    }

    public abstract int generateNextStepX();
    public abstract int generateNextStepY();
}
