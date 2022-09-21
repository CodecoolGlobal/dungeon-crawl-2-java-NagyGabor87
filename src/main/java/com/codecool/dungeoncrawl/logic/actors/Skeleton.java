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
        this.health = 20;
        this.damage = 6;
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

    @Override
    protected void makeNoise() {
        if (RandomGenerator.generateNumberBetween100And1() <= percentOfChanceToMakeSound) {
            makeSound(Sound.SKELETON_SOUND.getFilePath());
        }
    }
}
