package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Skeleton extends Monster {
    private Cell cell;
    private ActorType type;

    public Skeleton(Cell cell) {
        super(cell, 10, 2);
        this.cell = cell;
        this.type=ActorType.SKELETON;
    }

    @Override
    public char getSymbol() {
        return this.type.getSymbol();
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
                nextCell.getActor().damageHealth(getDamage());
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            super.cell = nextCell;
        }
    }

    @Override
    public void moveMonsters(Player player) {
        getRandomDirection();
    }

    public void getRandomDirection() {
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
