package com.codecool.dungeoncrawl.logic;

import java.util.Objects;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SHRINE("shrine"),
    LADDER("ladder"),
    ITEM("item"),
    HOLE("hole");
    ITEM("item"),
    DOOR("door");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName(Cell cell) {
        if (Objects.equals(tileName, "item")) {
            return cell.getItem().getTileName();
        }
        if (Objects.equals(tileName, "door")) {
            return cell.getTileName();
        }
        return tileName;
    }
}
