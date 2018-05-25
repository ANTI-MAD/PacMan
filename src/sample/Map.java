package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс необходим для получения и обработки карты.
 */

public class Map {

    private int width;
    private int height;
    private Tile[][] tiles;
    private Image map;
    private boolean[][] bool;

    private List<Apple> apples;
    private List<Enemy> enemies;

    public Map() {

        map = new Image(getClass().getResourceAsStream("map.png"));
        this.width = (int) map.getWidth();
        this.height = (int) map.getHeight();
        apples = new ArrayList<>();
        enemies = new ArrayList<>();
        bool = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bool[i][j] = true;
            }
        }
        tiles = new Tile[width][height];
    }

    public void render(final GraphicsContext graphicsContext) {
        getPixel();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y] != null) tiles[x][y].render(graphicsContext);
            }
        }

        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).render(graphicsContext);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(graphicsContext);
        }
    }

    public void getPixel() {
        int[] pixels = new int[width * height];

        PixelReader reader = map.getPixelReader();
        reader.getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {

                if (bool[xx][yy]) {
                    int val = pixels[xx + (yy * width)];

                    if (val == -16777216) { //чёрный цвет на картинке map.png
                        //Tile
                        tiles[xx][yy] = new Tile(xx * 32, yy * 32);
                    } else if (val == -1) { // белый цвет
                        //Apple
                        apples.add(new Apple(xx * 32, yy * 32));
                    } else if (val == -16776961) { // синий цвет
                        //Player
                        Game.getPlayer().mySetX(xx * 32);
                        Game.getPlayer().mySetY(yy * 32);
                    } else if (val == -65536) { // красный цвет
                        //Enemy
                        enemies.add(new Enemy(xx * 32, yy * 32));
                    }
                    bool[xx][yy] = false;
                }
            }
        }
    }

    public void tick() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Apple> getApples() {
        return apples;
    }

    public boolean[][] getBool() {
        return bool;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
