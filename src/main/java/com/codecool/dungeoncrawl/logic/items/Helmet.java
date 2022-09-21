package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {

    private final int armor;

    public Helmet(Cell cell) {
        super(cell);
        this.armor = 5;
    }

    @Override
    public String getTileName() {
        return "helmet";
    }

    public int getArmor() {
        return armor;
    }
}
