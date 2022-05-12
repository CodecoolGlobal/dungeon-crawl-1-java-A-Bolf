package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Monsters extends Actor {

    public Monsters(Cell cell, int health, int damage) {
        super(cell, health, damage);
    }
    public abstract void moveMonsters();
}
