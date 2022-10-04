package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class StrongestPotion extends Potion {

    public StrongestPotion(Cell cell) {
        super(cell);
        this.healing = 100;
    }

    public int getHealing() {
        return healing;
    }
    @Override
    public String getTileName() {
        return CellType.STRONGEST_POTION.getTileName();
    }

    @Override
    public char getTileCharacter() {
        return CellType.STRONGEST_POTION.getCharacter();
    }
}
