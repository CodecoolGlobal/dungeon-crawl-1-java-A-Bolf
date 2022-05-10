package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    private Random random = new Random();

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void moveSkeleton() {
        int dx = random.nextInt(2) - 1;
        int dy;
        if (dx == 0) {
            dy = random.nextInt(2) - 1;
        } else {
            dy = 0;
        }
        move(dx, dy);
    }
}

