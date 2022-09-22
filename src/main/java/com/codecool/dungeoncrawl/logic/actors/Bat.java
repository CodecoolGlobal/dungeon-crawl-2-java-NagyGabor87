package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

public class Bat extends Monster {


    public Bat(Cell cell) {
        super(cell);
        this.health = 10 + RandomGenerator.generateRandomHealthModifier();
        this.damage = 5 + RandomGenerator.generateRandomDamageModifier();
    }

    @Override
    protected void makeNoise() {
        // Doesn't make noise
    }

    @Override
    public String getTileName() {
        return CellType.BAT.getTileName();
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
