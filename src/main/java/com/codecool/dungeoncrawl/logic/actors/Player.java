package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private int health;
    private static int damage = 5;

    private ArrayList<Item> inventory;

    public static int getDamage() {
        return damage;
    }

    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
        health = 10;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public String getTileName() {
        return "player";
    }

    public void setItem(Item item) {
        inventory.add(item);
    }

}
