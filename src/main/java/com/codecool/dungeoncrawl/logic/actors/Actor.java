package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Sound;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int damage;
    public Actor(Cell cell, int health, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.damage = damage;

    }
    public abstract void move(int dx, int dy);

    protected void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        cell.setActor(null);
        cell = null;
        if(this instanceof Player){
            Sound.DEATH.play(false);
            Main.setGameOver();
        }
    }


    public void increaseDamage(int damage) {
        this.damage += damage;
    }

    public void increaseHealth(int health) {
        this.health += health;
    }

    public int getDamage() {
        return damage;
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

    protected void combat(Actor enemy){
        enemy.damageHealth(damage);
        damageHealth(enemy.getDamage());
        slashSound(enemy);
    }
    private void slashSound(Actor enemy){
        if(enemy instanceof Skeleton){
            Sound.SWORD_SLASH.play(false);
        }else if(enemy instanceof Ogre){
            Sound.SLASH_OGRE.play(false);
        }else{
            Sound.SLASH_BLOB.play(false);
        }
    }


}
