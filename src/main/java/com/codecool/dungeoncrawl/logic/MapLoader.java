package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.Consumable;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private static Player player = null;
    private static boolean dontPlacePlayerToStarter = false;


    private void addPlayerFromSave(String name, int hp, int x, int y, List<Item> inventory){



    }

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
                            new Skeleton(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new Ogre(cell);
                            break;
                        case  'b':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.MAIN, false);
                            break;
                        case  '1':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.FOUR_WAY, true);
                            break;
                        case  '2':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.UP_DOWN, true);
                            break;
                        case  '3':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.LEFT_RIGHT, true);
                            break;
                        case  '4':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.UP_LEFT, true);
                            break;
                        case  '5':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.DOWN_LEFT, true);
                            break;
                        case  '6':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.UP_RIGHT, true);
                            break;
                        case  '7':
                            cell.setType(CellType.FLOOR);
                            new Blup(cell, BlupTypes.DOWN_RIGHT, true);
                            break;
                        case '@':
                            if(!dontPlacePlayerToStarter) {
                                cell.setType(CellType.FLOOR);
                                if (player == null) {
                                    player = new Player(cell, y, x);
                                    map.setPlayer(player);
                                } else {
                                    player.setStart(cell, y, x);
                                    map.setPlayer(player);
                                }
                            }else{
                                cell.setType(CellType.FLOOR);
                            }
                            break;
                        case 'F':
                            cell.setType(CellType.ITEM);
                            cell.setItem(new Consumable(cell));
                            break;
                        case 'W':
                            cell.setType(CellType.ITEM);
                            cell.setItem(new Weapon(cell));
                            break;
                        case 'D':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'K':
                            cell.setType(CellType.ITEM);
                            cell.setItem(new Key(cell));
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
                        case '~':
                            if(dontPlacePlayerToStarter) {
                                cell.setType(CellType.FLOOR);
                                    player.setStart(cell, y, x);
                                    map.setPlayer(player);
                            }else{
                                cell.setType(CellType.FLOOR);
                            }
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");

                    }
                }
            }
        }
        dontPlacePlayerToStarter = true;
        return map;
    }

}
