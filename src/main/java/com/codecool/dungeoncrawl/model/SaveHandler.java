package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

    public static void saveGame(String currentMap, Player player) throws IOException {
        GameState gameState = saveGameState(currentMap, player);
        exportGameState(gameState);
        System.out.println("Game saved");
    }

    public static String gameToString(String currentMap, Player player){
        GameState gameState=saveGameState(currentMap,player);
        return gameState.toString();

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
        inventoryString=inventoryString.replace("[", "").replace("]", "");
        String[] inventoryArray = inventoryString.split(",");
        for (String itemString : inventoryArray) {
            Item item = Item.createItemByName(itemString);
            inventory.add(item);
        }
        return inventory;
    }

}
