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
        for (int i = 0; i < directions.length; i++) {
            if (this.getCell().getNeighbor(directions[i][0], directions[i][1]).getType().equals(CellType.FLOOR)) {
                this.getCell().getNeighbor(directions[i][0], directions[i][1]).setType(CellType.STRONGEST_POTION);
                new StrongestPotion(this.getCell().getNeighbor(directions[i][0], directions[i][1]));
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
