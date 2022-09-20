package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;

public class Player extends Actor {

    private final ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.health = 50;
        this.damage = 5;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        // Prevent actor to leave map
        if (nextCell == null) {
            return;
        }
        if ((nextCell.getType() == CellType.WALL)) {
            return;
        }
        else if ((nextCell.getType() == CellType.CLOSED_DOOR)) {
            Key key = returnKey();
            if (key != null) {
                nextCell.setType(CellType.OPEN_DOOR);
                removeItem(key);
            }
            return;
        }
        if (nextCell.getActor() instanceof Monster) {
            attack(nextCell);
            return;
        }
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public String getTileName() {
        return "player";
    }

    public Key returnKey(){
        for (Item item: inventory) {
            if (item instanceof Key){
                return (Key) item;
            }
        }
        return null;
    }

    public void removeItem(Item item) {
        if (item != null) {
            inventory.remove(item);
        }
    }

    public void setItem(Item item) {
        inventory.add(item);
    }

    public void addInventory(Item item) {
        if (item != null){
            inventory.add(item);
        }
    }

    public String inventoryToString(){
        StringBuilder sb = new StringBuilder("| ");
        for (Item item: inventory) {
            sb.append(item.getTileName()).append(" |");
        }
        return sb.toString();
    }

}
