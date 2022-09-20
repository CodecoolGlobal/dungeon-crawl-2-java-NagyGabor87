package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public int generateNextStep() {
        int max = 2;
        int min = -1;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
