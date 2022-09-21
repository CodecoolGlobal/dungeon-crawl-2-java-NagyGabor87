package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Key extends Item {


    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return CellType.KEY.getTileName();
    }
}
