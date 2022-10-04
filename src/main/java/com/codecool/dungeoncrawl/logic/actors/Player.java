package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Movable;
import com.codecool.dungeoncrawl.logic.Sound;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player extends Actor implements Movable {
    private static final int MAX_HEALTH = 150;


    private final List<Item> inventory;
    private int armor;
    private String tileName;
    private boolean isAlive = true;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new ArrayList<>();
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
        if (isNoCollision(nextCell)) {
            return;
        }
        else if ((nextCell.getType() == CellType.CLOSED_DOOR)) {
            if (findKey()) {
                Key key = returnKey();
                nextCell.setType(CellType.OPEN_DOOR);
                removeItem(key);
                makeSound(Sound.DOOR.getFilePath());
            }
            return;
        }
        if (nextCell.getActor() instanceof Monster) {
            attack(nextCell);
            return;
        }
        if (nextCell.getActor() instanceof PotionSeller) {
            ((PotionSeller) nextCell.getActor()).givePotion();
            return;
        }
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private static boolean isNoCollision(Cell nextCell) {
        return nextCell.getType() == CellType.WALL ||
                nextCell.getType() == CellType.DISAPPEARING_WALL;
    }

    public String getTileName() {
        return tileName;
    }

    private void setTileName(String tileName) {
        this.tileName = tileName;
    }

    private boolean findKey() {
        for (Item item: inventory) {
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }

    public Key returnKey(){
        Key key = null;
        for (Item item: inventory) {
            if (item instanceof Key){
                key = (Key) item;
            }
        }
        return key;
    }

    public void removeItem(Item item) {
        if (item != null) {
            inventory.remove(item);
        }
    }

    public void addItemToInventory(Item item) {
        if (item instanceof Potion) {
            changePlayerStats(item);
            makeSound(Sound.POTION.getFilePath());
        }
        else if (item != null){
            inventory.add(item);
            makeSound(Sound.PICK_UP_ITEM.getFilePath());
            changePlayerGraphics();
            changePlayerStats(item);
        }
    }

    private void attack(Cell nextCell) {
        if (hasAdvancedWeapon()) {
            makeSound(Sound.ADVANCED_ATTACK.getFilePath());
        } else {
            makeSound(Sound.BASIC_ATTACK.getFilePath());
        }

        // monster's attack is decreased by the players armor but 3 attack is guaranteed
        int monsterAttack = Math.max(nextCell.getActor().getDamage() - armor, 3);

        setHealth(getHealth() - monsterAttack);
        nextCell.getActor().setHealth(nextCell.getActor().getHealth() - getDamage());
        if (nextCell.getActor().getHealth() <= 0) {
            if(nextCell.getActor() instanceof Skeleton){
                nextCell.getGameMap().removeMonsterFromMovableMonsters((MovableMonster) nextCell.getActor());
            }
            nextCell.removeActor();
            makeSound(Sound.DEATH.getFilePath());
        }
    }

    private void changePlayerStats(Item item) {
        if (item instanceof DefensiveWeapon) {
            DefensiveWeapon defensiveWeapon = (DefensiveWeapon) item;
            this.armor += defensiveWeapon.getArmor();
        } else if (item instanceof AttackWeapon) {
            AttackWeapon attackWeapon = (AttackWeapon) item;
            this.damage += attackWeapon.getDamage();
        } else if(item instanceof Potion) {
            Potion potion = (Potion) item;
            this.health = Math.min(this.health + potion.getHealing(), MAX_HEALTH);
        }
    }

    private void changePlayerGraphics() {
        boolean hasHelmet = hasItem(CellType.HELMET.getTileName());
        boolean hasSword = hasItem(CellType.SWORD.getTileName());
        if (hasHelmet && hasSword) {
            setTileName(CellType.PLAYER_SWORD_AND_HELMET.getTileName());
        } else if (hasHelmet) {
            setTileName(CellType.PLAYER_HELMET.getTileName());
        } else if (hasSword) {
            setTileName(CellType.PLAYER_SWORD.getTileName());
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

    public boolean hasAdvancedWeapon() {
        for (Item item: inventory) {
            if (item instanceof AttackWeapon) {
                return true;
            }
        }
        return false;
    }


    public String inventoryToString(){
        StringBuilder sb = new StringBuilder();
        for (Item item: inventory) {
            sb.append("- ").append(item.getTileName()).append("\n");
        }
        return sb.toString();
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (this.health <= 0) this.isAlive=false;
    }

    public String getName() {
        // TODO:implement getname
        return "Implement me";
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void resetAlive() {
        // reset health after death to be able to move in the menu map.
        this.isAlive = true;
    }

    public String getInventory() {
        StringJoiner joiner = new StringJoiner(",");
        for (Item item: inventory) {
            joiner.add(item.getTileName());
        }
        return joiner.toString();
    }
}
