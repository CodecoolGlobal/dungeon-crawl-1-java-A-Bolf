package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Consumable;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player extends Actor implements CanPickupItems {
    private static Cell cell;
    private static int vertical;
    private static int horizontal;
    private static ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Consumable> consumables = new ArrayList<>();
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private Key key;


    public void setStart(Cell cell, int vertical, int horizontal) {
        this.cell = cell;
        this.vertical = vertical;
        this.horizontal = horizontal;
        cell.setActor(this);
    }

    public Player(Cell cell, int vertical, int horizontal) {
        super(cell, 100, 5);
        this.cell = cell;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

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
            super.cell = nextCell;
            vertical = vertical + dy;
            horizontal = horizontal + dx;
        }
    }

    public static String getInventoryContents() {
        String ret = "";
        for (Item item : inventory) {
            ret += item.toString() + " ";
        }
        return ret;
    }


    public void pickUpItem(Cell cell) {
        if (!cell.hasItem()){
            return;
        }
        Item item = cell.getItem();
        if (item.getTileName().equals("food")) {
            consumables.add((Consumable) item);
        } else if (item.getTileName().equals("sword")) {
            weapons.add((Weapon) item);
        } else if (item.getTileName().equals("key")) {
            key = (Key) item;
        }
        updateStats();
        cell.removeItem();

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


    public static boolean collised(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.isWalkable()) {
            if (nextCell.isAttackable()) {
                return false;
            } else if (nextCell.getType() == CellType.DOOR) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean passage() {
        Cell nextCell = cell.getNeighbor(0, 0);
        if (nextCell.isPassage()) {
            return true;
        }
        return false;
    }


    public String getTileName() {
        return "player";
    }

    public static int getHorizontal() {
        return horizontal;
    }

    public static int getVertical() {
        return vertical;
    }


}