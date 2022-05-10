package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private Cell cell;

    public Skeleton(Cell cell) {
        super(cell, 10, 2);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isWalkable()) {
            if (nextCell.isAttackable()) {
                combat(nextCell.getActor());
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void combat(Actor enemy) {
        enemy.damageHealth(super.getDamage());
        damageHealth(enemy.getDamage());
    }
}
