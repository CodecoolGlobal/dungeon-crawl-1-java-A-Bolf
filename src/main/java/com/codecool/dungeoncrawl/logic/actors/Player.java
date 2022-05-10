package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemType;

import java.util.LinkedList;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell,100,5);
    }
    private static LinkedList<Item> inventory= new LinkedList<>();

    public static void pickUpItem(Item item){
        inventory.add(item);
    }
    public static LinkedList<Item> getInventory(){
        return inventory;
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public String getTileName() {
        return "player";
    }
}
