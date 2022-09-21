package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class DefensiveWeapon extends Item {

    protected int armor;
    public DefensiveWeapon(Cell cell) {
        super(cell);
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
