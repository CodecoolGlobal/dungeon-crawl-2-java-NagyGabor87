package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import javax.sound.sampled.*;
import java.io.File;

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


    protected void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public void makeSound(String filepath) {
        try{
            final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    clip.close();
            });

            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));
            clip.start();
        }
        catch (Exception exc) {
            exc.printStackTrace(System.out);
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
