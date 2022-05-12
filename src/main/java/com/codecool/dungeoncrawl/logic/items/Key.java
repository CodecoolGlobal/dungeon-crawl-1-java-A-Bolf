package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.DoorType;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell);
    }
public void openDoor(Cell cell) {
        cell.setType(CellType.DOOR);
        cell.setDoorStatus(DoorType.OPEN);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
