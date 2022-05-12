package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Blup;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Consumable;
import com.codecool.dungeoncrawl.logic.items.Weapon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {

    public static List<Skeleton> getSkeletons() {
        return skeletons;
    }
    public static List<Ogre> getOgres() {
        return ogres;
    }
    public static List<Blup> getBlups() {return blups;}

    private static final List<Blup> blups = new ArrayList<>();
    private static final List<Skeleton> skeletons = new ArrayList<>();
    private static final List<Ogre> ogres = new ArrayList<>();
    public static GameMap loadMap(String givenMap) {
        InputStream is = MapLoader.class.getResourceAsStream(givenMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            skeletons.add(new Skeleton(cell));
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            ogres.add(new Ogre(cell));
                            break;
                        case  'b':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, "mainBlup");
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell,y,x));
                            break;
                        case 'F':
                            cell.setType(CellType.ITEM);
                            cell.setItem(new Consumable(cell));
                            break;
                        case 'W':
                            cell.setType(CellType.ITEM);
                            cell.setItem(new Weapon(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.SHRINE);
                            break;
                        case 'l':
                            cell.setType(CellType.LADDER);
                            break;
                        case 'h':
                            cell.setType(CellType.HOLE);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
