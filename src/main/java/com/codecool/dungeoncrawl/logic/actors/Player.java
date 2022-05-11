package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Consumable;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.util.ArrayList;

public class Player extends Actor implements CanPickupItems {
    private Cell cell;
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Consumable> consumables = new ArrayList<>();
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private Key key;

    public Player(Cell cell) {
        super(cell, 100, 5);
        this.cell = cell;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isWalkable()) {
            if (nextCell.isAttackable()) {
                super.combat(nextCell.getActor());
                return;
            }
            if (nextCell.hasItem()) {
                pickUpItem(nextCell);
                nextCell.removeItem();
            }
            if (nextCell.getType() == CellType.DOOR) {
                if (key != null) {
                    key.openDoor(nextCell);
                } else {
                    return;
                }
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void pickUpItem(Cell cell) {
        Item item = cell.getItem();
        if (item.getTileName().equals("food")) {
            consumables.add((Consumable) item);
        } else if (item.getTileName().equals("sword")) {
            weapons.add((Weapon) item);
        } else if (item.getTileName().equals("key")) {
            key = (Key) item;
        }
        updateStats();
    }

    private void updateStats() {
        for (Weapon weapon : weapons) {
            increaseDamage(weapon.getDamage());
            inventory.add(weapon);
        }
        weapons.clear();
        for (Consumable consumable : consumables) {
            increaseHealth(consumable.getHealthValue());
            inventory.add(consumable);
        }
        consumables.clear();
    }

    public String getTileName() {
        return "player";
    }
}