package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;

public class Player extends Actor {

    private final ArrayList<Item> inventory = new ArrayList<>();
    private int armor;
    private String tileName;

    public Player(Cell cell) {
        super(cell);
        this.health = 50;
        this.damage = 5;
        this.armor = 5;
        this.tileName = CellType.PLAYER.getTileName();
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
        return tileName;
    }

    private void setTileName(String tileName) {
        this.tileName = tileName;
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
        changePlayerGraphics();
    }

    private void changePlayerGraphics() {
        boolean hasHelmet = hasItem(CellType.HELMET.getTileName());
        boolean hasSword = hasItem(CellType.SWORD.getTileName());
        if (hasHelmet && !hasSword) {
            setTileName(CellType.PLAYER_HELMET.getTileName());
        } else if (!hasHelmet && hasSword){
            setTileName(CellType.PLAYER_SWORD.getTileName());
        } else if (hasHelmet && hasSword) {
            setTileName(CellType.PLAYER_SWORD_AND_HELMET.getTileName());
        }
    }

    private boolean hasItem(String itemName) {
        for (Item item: inventory) {
            if (item.getTileName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


    public String inventoryToString(){
        StringBuilder sb = new StringBuilder("| ");
        for (Item item: inventory) {
            sb.append(item.getTileName()).append(" |");
        }
        return sb.toString();
    }

}
