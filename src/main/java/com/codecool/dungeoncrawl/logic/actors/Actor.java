package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Sound;

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

    public abstract void move(int dx, int dy);

    protected void setHealth(int health) {
        this.health = health;
    }

    public void attack(Cell nextCell) {
        if (this instanceof Player) {
            if (nextCell.getActor() instanceof Monster) {
                Player player = (Player) this;
                if (player.hasAdvancedWeapon()) {
                    makeSound(Sound.ADVANCED_ATTACK.getFilePath());
                } else {
                    makeSound(Sound.BASIC_ATTACK.getFilePath());
                }

                this.setHealth(getHealth() - nextCell.getActor().getDamage());
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - player.getDamage());
                if (nextCell.getActor().getHealth() <= 0) {
                    nextCell.removeActor();
                    makeSound(Sound.DEATH.getFilePath());
                }
            }
        }
    }

    public void makeSound(String filepath) {
        try{
            final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
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
