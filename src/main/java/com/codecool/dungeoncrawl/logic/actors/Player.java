package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
    }

    public String getTileName() {
        return "player";
    }

    public void setItem(Item item) {
        inventory.add(item);
    }

}
