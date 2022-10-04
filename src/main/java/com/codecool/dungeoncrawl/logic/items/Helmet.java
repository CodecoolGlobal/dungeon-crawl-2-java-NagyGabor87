package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Helmet extends DefensiveWeapon {



    public Helmet(Cell cell) {
        super(cell);
        this.armor = 5;
    }

    @Override
    public String getTileName() {
        return CellType.HELMET.getTileName();
    }

    @Override
    public char getTileCharacter() {
        return CellType.HELMET.getCharacter();
    }


}
