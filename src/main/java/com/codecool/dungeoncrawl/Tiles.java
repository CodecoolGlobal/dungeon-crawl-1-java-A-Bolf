package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;
    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("peasant", new Tile(27, 0));
        tileMap.put("knight", new Tile(28, 0));
        tileMap.put("templar", new Tile(31, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("shrine", new Tile(2, 14));
        tileMap.put("ladder", new Tile(21, 1));
        tileMap.put("hole", new Tile(17, 17));
        tileMap.put("sword", new Tile(0,30));
        tileMap.put("food", new Tile(15,29));
        tileMap.put("ogre", new Tile(30, 6));
        tileMap.put("mainBlup",new Tile(14, 5));
        tileMap.put("fourWayBlup", new Tile(8,3));
        tileMap.put("upDownBlup",new Tile(12,5));
        tileMap.put("leftRightBlup",new Tile(12, 3));
        tileMap.put("downRightBlup",new Tile(13, 5));
        tileMap.put("downLeftBlup",new Tile(11, 3));
        tileMap.put("upRightBlup",new Tile(9,3));
        tileMap.put("upLeftBlup",new Tile(10,3));
        tileMap.put("door:closed", new Tile(3,9));
        tileMap.put("door:open", new Tile(6,9));
        tileMap.put("key", new Tile(16,23));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
