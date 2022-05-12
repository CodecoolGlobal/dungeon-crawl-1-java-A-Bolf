package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private DoorType doorStatus;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
        if (type == CellType.DOOR) {
            doorStatus = DoorType.CLOSED;
        }
    }

    public Item getItem() {
        return item;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
        if (type == CellType.DOOR) {
            doorStatus = DoorType.CLOSED;
        }
    }
    public void setDoorStatus(DoorType doorStatus) {
        this.doorStatus = doorStatus;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        if (type == CellType.DOOR) {
            return doorStatus.getTileName();
        }
        return type.getTileName(this);
    }


    public boolean hasItem() {
        return this.item != null;
    }

    public void removeItem() {
        this.item = null;
        this.type = CellType.FLOOR;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public boolean isWalkable() {
        return type == CellType.FLOOR || type == CellType.ITEM || type == CellType.LADDER|| type == CellType.HOLE|| type == CellType.SHRINE|| type == CellType.DOOR;
    }
    public boolean isAttackable() {
        return actor != null;
    }
}