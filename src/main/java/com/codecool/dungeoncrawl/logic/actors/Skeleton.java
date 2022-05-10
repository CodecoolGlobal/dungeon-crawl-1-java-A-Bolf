package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell,10,2);
    }

    private Random random = new Random();

    @Override
    public String getTileName() {
        return "skeleton";
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

