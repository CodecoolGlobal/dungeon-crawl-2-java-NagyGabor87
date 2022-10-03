package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Move;
import com.codecool.dungeoncrawl.logic.Sound;
import com.codecool.dungeoncrawl.logic.util.RandomGenerator;

import java.util.Random;


public class Skeleton extends Monster implements Move {
    
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
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        // Prevent actor to leave map
        if (nextCell == null) {
            return;
        }

        // Prevent actor to collide wall or other actors
        if (isNextCellOccupied(nextCell)) return;

        cell.setActor(null);
        nextCell.setActor(this);
        this.cell = nextCell;
        makeNoise();
    }
    private static boolean isNextCellOccupied(Cell nextCell) {
        return (nextCell.getType() == CellType.WALL) ||
                nextCell.getType() == CellType.DISAPPEARING_WALL ||
                nextCell.getType() == CellType.CLOSED_DOOR ||
                nextCell.getType() == CellType.POTION_SELLER ||
                nextCell.getActor() != null;
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
