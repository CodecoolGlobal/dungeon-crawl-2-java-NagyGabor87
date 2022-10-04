package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Movable;

public abstract class MovableMonster extends Monster implements Movable {

    public MovableMonster(Cell cell) {
        super(cell);
    }


     @Override   public abstract void move(int dx, int dy);
}
