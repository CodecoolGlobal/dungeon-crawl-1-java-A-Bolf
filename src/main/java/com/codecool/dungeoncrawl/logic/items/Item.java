package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    protected ItemType type;

    public Item(Cell cell) {

        this.cell = cell;
        this.cell.setItem(this);
    }

    private boolean isPlaceable(Cell cell, String itemName) {
        return !cell.hasItem();
    }

    public abstract String getTileName();

}
