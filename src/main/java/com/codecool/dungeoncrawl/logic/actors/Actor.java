package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        // Prevent actor to leave map
        if (nextCell == null) {
            return;
        }
        if (this instanceof Player) {
            if ((nextCell.getType() == CellType.WALL)) {
                return;
            }
            if (nextCell.getActor() instanceof Monster) {
                attack(nextCell);
                return;
            }
        } else {
            if ((nextCell.getType() == CellType.WALL) || nextCell.getActor() instanceof Actor) return;
        }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
    }

    public abstract void setHealth(int health);

    private void attack(Cell nextCell) {
        if (this instanceof Player) {
            if (nextCell.getActor() instanceof Monster) {
                this.setHealth(getHealth() - Monster.getDamage());
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - Player.getDamage());
                if (nextCell.getActor().getHealth() == 0) {
                    nextCell.removeActor();
                }
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
