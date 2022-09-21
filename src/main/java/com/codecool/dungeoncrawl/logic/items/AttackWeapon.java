package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class AttackWeapon extends Item {
    protected int damage;

    public AttackWeapon(Cell cell) {
        super(cell);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
