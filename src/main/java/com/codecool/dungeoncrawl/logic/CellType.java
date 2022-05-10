package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    ITEM("item");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName(Cell cell) {
        if (tileName=="item")
            return cell.getItem().getTileName();
        return tileName;
    }
}
