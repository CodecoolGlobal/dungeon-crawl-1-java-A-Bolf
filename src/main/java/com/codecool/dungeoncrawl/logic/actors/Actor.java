package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int damage;

    public Actor(Cell cell,int health,int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health=health;
        this.damage=damage;

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isWalkable(nextCell)) {
            if (isAttackable(nextCell)) {
                combat(nextCell.getActor());
                return;
            }
            if (nextCell.hasItem()) {
                pickupItem(nextCell);
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void pickupItem(Cell cell) {
        Player.pickUpItem(cell.getItem());
        cell.removeItem();
        System.out.println(Player.getInventory());
    }



    private void damageHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    private void die() {
        cell.setActor(null);
        cell = null;
    }

    private void combat(Actor monster) {
        monster.damageHealth(damage);
        damageHealth(monster.getDamage());
    }

    private boolean isWalkable(Cell cell) {
        return cell.getType() == CellType.FLOOR||cell.getType() == CellType.ITEM;
    }

    private boolean isAttackable(Cell cell) {
        return cell.getActor() != null;
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
}
