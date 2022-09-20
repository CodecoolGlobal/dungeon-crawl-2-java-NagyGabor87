package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

public abstract class Monster extends Actor {
    private static int damage = 2;
    protected int health;

    public static int getDamage() {
        return damage;
    }


    public Monster(Cell cell) {
        super(cell);
    }

    public abstract int generateNextStepX();
    public abstract int generateNextStepY();
}
