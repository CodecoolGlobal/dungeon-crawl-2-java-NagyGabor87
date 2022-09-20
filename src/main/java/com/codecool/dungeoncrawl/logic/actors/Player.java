package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory = new ArrayList<>();;
    private int health;
    private static int damage = 5;

    public static int getDamage() {
        return damage;
    }

    public Player(Cell cell) {
        super(cell);
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

    public void addInventory(Item item) {
        inventory.add(item);
    }

    public String inventoryToString(){
        StringBuilder sb = new StringBuilder("| ");
        for (Item item: inventory) {
            sb.append(item.getTileName()).append(" |");
        }
        return sb.toString();
    }

}
