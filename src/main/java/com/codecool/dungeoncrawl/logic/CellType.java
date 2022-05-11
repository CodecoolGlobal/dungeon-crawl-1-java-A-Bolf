package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SHRINE("shrine"),
    LADDER("ladder"),
    ITEM("item"),
    HOLE("hole");

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
