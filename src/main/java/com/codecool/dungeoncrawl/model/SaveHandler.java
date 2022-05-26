package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveHandler {
    private static final String SAVE_FILE_NAME = "save.txt";
    private static final String SAVE_FILE_PATH = "src/main/resources";
    private static final String SAVE_FILE_PATH_WITH_NAME = SAVE_FILE_PATH + SAVE_FILE_NAME;

    public static void exportGameState(@NotNull GameState gameState) throws IOException {
        File saveFile = new File(SAVE_FILE_PATH_WITH_NAME);
        saveFile.createNewFile();
        String saveString = gameState.toString();
        FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH_WITH_NAME);
        fileWriter.write(saveString);
        fileWriter.close();
    }

    public static @NotNull GameState saveGameState(String currentMap, Player player) {
        PlayerModel playermodel = new PlayerModel(player);
        return new GameState(currentMap, new Date(System.currentTimeMillis()), playermodel);
    }

    public static void saveToDB(GameMap map) {
        String[] gameStrings = gameToString(map.toString(), map.getPlayer());
        HashMap<String, String> gameSave = new HashMap<>();
    }


    public static void saveGame(GameMap map) throws IOException {
        GameState gameState = saveGameState(map.toString(), map.getPlayer());
        exportGameState(gameState);
        System.out.println("Game saved");
    }

    public static String[] gameToString(String currentMap, Player player) {
        String[] save = new String[2];
        save[0] = saveGameState(currentMap, player).toString();
        save[1] = saveGameState(currentMap, player).getPlayer().toString();
        return save;
    }

    public static GameState loadGame() {
        return GameState.fromString(readSaveFile());
    }

    private static String readSaveFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(SAVE_FILE_PATH_WITH_NAME);
            int c;
            while ((c = fileReader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<Item> inventoryFromString(String inventoryString) {
        List<Item> inventory = new ArrayList<>();
        inventoryString = inventoryString.replace("[", "").replace("]", "");
        String[] inventoryArray = inventoryString.split(",");
        for (String itemString : inventoryArray) {
            Item item = Item.createItemByName(itemString);
            inventory.add(item);
        }
        return inventory;
    }


}
