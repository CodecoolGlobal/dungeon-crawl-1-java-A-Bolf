package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.LinkedList;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }
    private static LinkedList<Item> inventory= new LinkedList<>();

    public static String getInventoryContents(){
        String ret ="";
        for (Item item : inventory){
            ret+=item.toString()+" ";
        }
        return ret;
    }

    public void pickUpItem(Item item){
        inventory.add(item);
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public String getTileName() {
        return "player";
    }
}
