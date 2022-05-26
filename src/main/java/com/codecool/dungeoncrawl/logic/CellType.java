package com.codecool.dungeoncrawl.logic;

import java.util.Objects;

public enum CellType {
    EMPTY("empty",' '),
    FLOOR("floor",'.'),
    WALL("wall",'#'),
    SHRINE("shrine",'S'),
    LADDER("ladder",'l'),
    ITEM("item",'i'),
    HOLE("hole",'h'),
    DOOR("door",'D');

    private final String tileName;
    private final char symbol;

    CellType(String tileName,char symbol) {
        this.tileName = tileName;
        this.symbol=symbol;
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

    public char getSymbol(){
        return this.symbol;
    }
}
