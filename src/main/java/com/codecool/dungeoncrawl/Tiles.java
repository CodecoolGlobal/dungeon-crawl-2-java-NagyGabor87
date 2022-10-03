package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
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
        tileMap.put("disappearing_wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("closed_door", new Tile(8, 9));
        tileMap.put("open_door", new Tile(9, 9));
        tileMap.put("player_simple", new Tile(26, 0));
        tileMap.put("player_sword", new Tile(27, 0));
        tileMap.put("player_helmet_and_sword", new Tile(28, 0));
        tileMap.put("player_helmet", new Tile(29, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("helmet", new Tile(4, 22));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("bat", new Tile(26, 8));
        tileMap.put("quit", new Tile(22,31));
        tileMap.put("repeat", new Tile(23,31));
        tileMap.put("potion", new Tile(25,23));
        tileMap.put("strongest_potion", new Tile(26,23));
        tileMap.put("potion_seller", new Tile(29,3));
        tileMap.put("spiderweb", new Tile(2,15));
        tileMap.put("torch_a", new Tile(3,15));
        tileMap.put("torch_b", new Tile(4,15));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
