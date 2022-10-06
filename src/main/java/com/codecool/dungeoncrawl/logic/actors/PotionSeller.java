package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.StrongestPotion;

public class PotionSeller extends Actor {

    public PotionSeller(Cell cell) {
        super(cell);
    }

    public void givePotion() {
        int[][] directions = {{0,1}, {-1,0}, {1,0}, {0,-1}};
        for (int[] direction : directions) {
            if (this.getCell().getNeighbor(direction[0], direction[1]).getType().equals(CellType.FLOOR)) {
                this.getCell().getNeighbor(direction[0], direction[1]).setType(CellType.STRONGEST_POTION);
                new StrongestPotion(this.getCell().getNeighbor(direction[0], direction[1]));
                break;
            }
        }
    }

    @Override
    public String getTileName() {
        return CellType.POTION_SELLER.getTileName();
    }
    @Override
    public char getTileCharacter() {
        return CellType.POTION_SELLER.getCharacter();
    }
}
