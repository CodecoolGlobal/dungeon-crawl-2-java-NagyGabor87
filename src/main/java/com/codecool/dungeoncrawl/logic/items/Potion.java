package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Potion extends HealingItems {

    public Potion(Cell cell) {
        super(cell);
        this.healing = 25;
    }

    public int getHealing() {
        return healing;
    }

    @Override
    public String getTileName() {
        return CellType.POTION.getTileName();
    }
}
