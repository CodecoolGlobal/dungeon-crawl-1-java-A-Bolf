package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Skeleton extends Actor {
    private Cell cell;

    public Skeleton(Cell cell) {
        super(cell, 10, 2);
        this.cell = cell;
    }

    private Random random = new Random();

    @Override
    public String getTileName() {
        return "skeleton";
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isWalkable()) {
            if (nextCell.isAttackable()) {
                super.combat(nextCell.getActor());
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    public void moveSkeleton() {
        int dx = random.nextInt(3) - 1;
        int dy;
        if (dx == 0) {
            dy = random.nextInt(3) - 1;
        } else {
            dy = 0;
        }
        moveMonster(dx, dy);
    }
}
