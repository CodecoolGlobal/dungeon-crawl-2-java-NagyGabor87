package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Sword extends AttackWeapon {

    public Sword(Cell cell) {
        super(cell);
        this.damage = 5;
    }

    @Override
    public String getTileName() {
        return CellType.SWORD.getTileName();
    }
}
