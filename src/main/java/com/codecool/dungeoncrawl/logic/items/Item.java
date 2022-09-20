package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Item implements Drawable {
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public void pickUp(){
        Actor actor = cell.getActor();
        if (actor instanceof Player){
            Player player = (Player) actor;
            player.setItem(this);
            cell.removeItem();
        }
    }

}
