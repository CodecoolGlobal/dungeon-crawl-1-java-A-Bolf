package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.sun.source.tree.InstanceOfTree;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int damage;

    public Actor(Cell cell, int health, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.damage = damage;

    }
    public abstract void move(int dx, int dy);


    public void moveMonster(int dx, int dy){
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() instanceof Player){
            combat(nextCell.getActor());
            return;
        }
        if (nextCell.getActor() != null){
            return;
        }
        if (nextCell.getType() == CellType.FLOOR) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    protected void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        cell.setActor(null);
        cell = null;
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
    }
}
