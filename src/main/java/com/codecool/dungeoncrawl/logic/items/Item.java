package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    protected String name;
    private Cell cell;
    protected ItemType type;

    public Item(Cell cell) {

        this.cell = cell;
        this.cell.setItem(this);
    }
    public Item(){
    }

    public static <I extends Item> I createItemByName(String name) {
        switch (name.toLowerCase()) {
            case "key":
                return (I) new Key();
            case "apple":
                return (I) new Consumable();
            case "sword":
                return (I) new Weapon();
            default:
                return null;
        }
    }

    private boolean isPlaceable(Cell cell, String itemName) {
        return !cell.hasItem();
    }

    public String toString() {
        return name;
    }

    public abstract String getTileName();

}
