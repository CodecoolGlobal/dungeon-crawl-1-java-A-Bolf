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
                System.out.println("Hit the actor "+nextCell.getActor());
                System.out.println("I'm "+cell.getActor());
                super.combat(nextCell.getActor());
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            super.cell = nextCell;
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
        if (dy != 0 || dx != 0){
            move(dx, dy);
        }

    }
}
