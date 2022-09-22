package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Sound;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

import java.util.Random;


public class Skeleton extends Monster {
    
    private final int percentOfChanceToMakeSound;

    public Skeleton(Cell cell) {
        super(cell);
        this.health = 20 + RandomGenerator.generateRandomHealthModifier();
        this.damage = 6 + RandomGenerator.generateRandomDamageModifier();
        this.percentOfChanceToMakeSound = 2;
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
        return RandomGenerator.generateRandomStep();
    }

    @Override
    public int generateNextStepY() {
        return RandomGenerator.generateRandomStep();
    }

    @Override
    protected void makeNoise() {
        if (RandomGenerator.generateNumberBetween100And1() <= percentOfChanceToMakeSound) {
            makeSound(Sound.SKELETON_SOUND.getFilePath());
        }
    }
}
