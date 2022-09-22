package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public abstract class HealingItems extends Item {
    protected int healing;


    public HealingItems(Cell cell) {
        super(cell);
    }

    public int getHealing() {
        return healing;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
