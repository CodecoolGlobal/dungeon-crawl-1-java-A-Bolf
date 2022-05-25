package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class GameState extends BaseModel {
    private Date savedAt;
    private String currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel player;

    public GameState(String currentMap, Date savedAt, PlayerModel player) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        discoveredMaps.add(currentMap);
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public static GameState fromString(String string) {
        String[] parts = string.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].split(":")[1];
        }
        GameState gameState = new GameState(parts[1], Date.valueOf(parts[0]), PlayerModel.fromString(parts[3]+","+parts[4]+","+parts[5]+","+parts[6]+","+parts[7]));
//        gameState.currentMap = parts[1];
//        gameState.savedAt = Date.valueOf(parts[0]);
//        gameState.player = new PlayerModel(parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        gameState.discoveredMaps.addAll(Arrays.asList(parts).subList(5, parts.length));
        return gameState;
    }
}
