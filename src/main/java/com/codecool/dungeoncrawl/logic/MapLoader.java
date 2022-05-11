package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    private static int starterHorizontal;
    private static int starterVertical;

    public static int getStarterHorizontal() {
        return starterHorizontal;
    }

    public static int getStarterVertical() {
        return starterVertical;
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
                        case '@':
                            cell.setType(CellType.FLOOR);
                            starterVertical = y;
                            starterHorizontal = x;
                            map.setPlayer(new Player(cell));
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
