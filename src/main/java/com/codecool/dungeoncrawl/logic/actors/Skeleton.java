package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;


public class Skeleton extends Monster {

    public Skeleton(Cell cell) {
        super(cell);
        this.health = 20;
        this.damage = 4;
    }

    @Override
    public String getTileName() {
        if (getHealth() <= 0) {
            return "floor";
        }
        return CellType.SKELETON.getTileName();
    }

    @Override
    public int generateNextStepX() {
        return generateRandomStep();
    }

    @Override
    public int generateNextStepY() {
        return generateRandomStep();
    }

    private int generateRandomStep(){
        int max = 2;
        int min = -1;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
