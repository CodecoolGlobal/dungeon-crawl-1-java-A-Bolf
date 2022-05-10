package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Consumable extends Item{
    private int healthValue=30;

    public Consumable(Cell cell) {
        super(cell);
        super.type=ItemType.FOOD;
        super.name="apple";
    }

    @Override
    public String getTileName() {
        return "food";
    }



}
