package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Sound;
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
    private byte swordCount = 0;
    private byte keyCount = 0;

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
                if ((keyCount > (byte)0 && Main.getMapNow()==0 )|| (keyCount > (byte)3 && Main.getMapNow()==(byte)1)) {
                    key.openDoor(nextCell);
                    Sound.UNLOCK.play(false);
                } else {
                    Sound.BUMP.play(false);
                    return;
                }
            }

            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            super.cell = nextCell;
            vertical = vertical + dy;
            horizontal = horizontal + dx;
            Sound.STEP.play(false);
        }else {
            Sound.BUMP.play(false);
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
            Sound.APPLE.play(false);
        } else if (item.getTileName().equals("sword")) {
            weapons.add((Weapon) item);
            swordCount++;
            Sound.SWORD.play(false);
        } else if (item.getTileName().equals("key")) {
            key = (Key) item;
            keyCount++;
            Sound.KEY.play(false);
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
        String playerAvatar ="peasant";
        if (swordCount>1){
            playerAvatar = "knight";
        }if(swordCount > 3){
            playerAvatar="templar";
        }
        return playerAvatar;
    }

    public static int getHorizontal() {
        return horizontal;
    }

    public static int getVertical() {
        return vertical;
    }

}