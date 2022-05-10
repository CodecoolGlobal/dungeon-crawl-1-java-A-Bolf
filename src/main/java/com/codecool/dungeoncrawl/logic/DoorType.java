package com.codecool.dungeoncrawl.logic;

public enum DoorType {
    CLOSED("door:closed"),
    OPEN("door:open");

    private final String tileName;
    DoorType(String tileName) {
        this.tileName = tileName;
    }
    public String getTileName() {
        return tileName;
    }
}
