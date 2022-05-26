package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int x;
    private int y;
    private List<Item> inventory = new ArrayList<>();

    public PlayerModel(String playerName, int hp, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public PlayerModel(Player player) {
        this.playerName = "player";
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.inventory = player.getInventory();

    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static PlayerModel fromString(String playerString) {
        String[] playerData = playerString.split(",");
        PlayerModel playerModel = new PlayerModel(playerData[0], Integer.parseInt(playerData[1]), Integer.parseInt(playerData[2]), Integer.parseInt(playerData[3]));
        playerModel.inventory = SaveHandler.inventoryFromString(playerData[4]);
        return playerModel;
    }

    public String toString(PlayerModel playerModel) {
        StringBuilder playerString = new StringBuilder();
        playerString.append(playerModel.getPlayerName()).append(",").append(playerModel.getHp()).append(",").append(playerModel.getX()).append(",").append(playerModel.getY()).append(",");
        for (Item item : playerModel.getInventory()) {
            playerString.append(item.getTileName()).append(",");
        }
        return playerString.toString();
    }

}
