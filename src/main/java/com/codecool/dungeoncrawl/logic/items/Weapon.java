package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item{
    private int damage=10;

    public Weapon(Cell cell) {
        super(cell);
        super.type=ItemType.SWORD;
    }

    @Override
    public String getTileName() {
        return "sword";
    }



}