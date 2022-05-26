package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Blup;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;


    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((width)+" "+(height)+System.lineSeparator());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (cells[x][y].getType()) {
                    case FLOOR:
                        if (cells[x][y].getActor() != null) {
                            sb.append(cells[x][y].getActor().getSymbol());
                        } else {
                            sb.append(CellType.FLOOR.getSymbol());
                        }
                        break;
                    case ITEM:
                        sb.append(cells[x][y].getItem().getType().getSymbol());
                        break;
                    default:
                        sb.append(cells[x][y].getType().getSymbol());
                        break;
                }

            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Monster> getMonsters(Class<?> monsterType) {
        List<Monster> monsters = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getActor() != null && cells[x][y].getActor().getClass().equals(monsterType)) {
                    Monster temp = (Monster) cells[x][y].getActor();
                    monsters.add(temp);
                }
            }
        }
        return monsters;
    }

}
