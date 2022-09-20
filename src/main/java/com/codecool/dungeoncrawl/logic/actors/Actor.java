package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private static int health;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (this instanceof Player) {
            if ((nextCell.getType() == CellType.WALL) || nextCell.getActor() instanceof Monster) {
                if (nextCell.getActor() instanceof Monster) {
                    attack(nextCell);
                }
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public abstract void setHealth(int health);

    private void attack(Cell nextCell) {
        if (this instanceof Player) {
            System.out.println("BANZAI!");
            if (nextCell.getActor() instanceof Monster) {
                this.setHealth(getHealth() - Monster.getDamage());
                System.out.println("hit");
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - Player.getDamage());
            }
        }
        }

    public abstract int getHealth();

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
