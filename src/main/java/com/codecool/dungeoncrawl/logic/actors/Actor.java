package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;

    protected int damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public int getDamage(){
        return damage;
    }

    public abstract void move(int dx, int dy);

    protected void setHealth(int health) {
        this.health = health;
    }

    public void attack(Cell nextCell) {
        if (this instanceof Player) {
            if (nextCell.getActor() instanceof Monster) {
                this.setHealth(getHealth() - nextCell.getActor().getDamage());
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - this.getDamage());
                if (nextCell.getActor().getHealth() <= 0) {
                    nextCell.removeActor();
                }
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
