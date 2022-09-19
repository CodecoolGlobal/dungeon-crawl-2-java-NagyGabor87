package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    private int damage;

    public Sword(Cell cell) {
        super(cell);
        this.damage = 10;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
