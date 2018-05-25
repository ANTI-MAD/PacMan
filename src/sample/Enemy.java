package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Класс расставляет приведения на поле и управляет их движением.
 */

public class Enemy extends Rectangle {
    private double x;
    private double y;

    /**
     * random - приведения движутся случайным образом
     * smart - приведения движутся за игроком
     */
    private int random = 0, smart = 1, findPath = 2;
    private int state = smart;
    private int right = 0, left = 1, up = 2, down = 3;
    /**
     * направление движения
     */
    private int dir = -1;

    private int time = 0;
    private int targetTime = 60 * 4;

    private int speed = 4;
    private int lastDir = -1;

    private Random randomGen;

    public Enemy(final double x, final double y) {
        setX(x);
        setY(y);
        setHeight(32);
        setWidth(32);
        this.x = x;
        this.y = y;

        randomGen = new Random();
        dir = randomGen.nextInt(4);
    }

    public void render(final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(x, y, 32, 32);
    }

    public void tick() {

        if (state == random) {
            if (dir == right) {
                if (canMove(x + speed, y)) {
                    if (randomGen.nextInt(100) < 50)  x += speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == left) {
                if (canMove(x - speed, y)) {
                    if (randomGen.nextInt(100) < 50) x -= speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == up) {
                if (canMove(x, y - speed)) {
                    if (randomGen.nextInt(100) < 50) y -= speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == down) {
                if (canMove(x, y + speed)) {
                    if (randomGen.nextInt(100) < 50) y += speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            time++;

            if (time == targetTime) {
                state = smart;
                time = 0;
            }
        }
        else if (state == smart) {
            //Follow the player
            boolean move = false;

            if (x < Game.getPlayer().myGetX()) {
                if (canMove(x + speed, y)) {
                    if (randomGen.nextInt(100) < 50) x += speed;
                    move = true;
                    lastDir = right;
                }
            }

            if (x > Game.getPlayer().myGetX()) {
                if (canMove(x - speed, y)) {
                    if (randomGen.nextInt(100) < 50) x -= speed;
                    move = true;
                    lastDir = left;
                }
            }

            if (y < Game.getPlayer().myGetY()) {
                if (canMove(x, y + speed)) {
                    if (randomGen.nextInt(100) < 50) y += speed;
                    move = true;
                    lastDir = down;
                }
            }

            if (y > Game.getPlayer().myGetY()) {
                if (canMove(x, y - speed)) {
                    if (randomGen.nextInt(100) < 50) y -= speed;
                    move = true;
                    lastDir = up;
                }
            }

            if (x == Game.getPlayer().myGetX() && y == Game.getPlayer().myGetY()) move = true;


            if (!move) {
                state = findPath;
            }

            time++;
            if (time == targetTime) {
                state = random;
                time = 0;
            }
        }

        else if (state == findPath) {

            if (lastDir == right) {
                if (y < Game.getPlayer().myGetY()) {
                    if (canMove(x, y + speed)) {
                        if (randomGen.nextInt(100) < 50) y += speed;
                        state = smart;
                    }
                }
                else {
                    if (canMove(x, y - speed)) {
                        if (randomGen.nextInt(100) < 50) y -= speed;
                        state = smart;
                    }
                }

                if (canMove(x + speed, y)) {
                    if (randomGen.nextInt(100) < 50) x += speed;
                }
            }
            else if (lastDir == left) {

                if (y < Game.getPlayer().myGetY()) {
                    if (canMove(x, y + speed)) {
                        if (randomGen.nextInt(100) < 50) y += speed;
                        state = smart;
                    }
                }
                else {
                    if (canMove(x, y - speed)) {
                        if (randomGen.nextInt(100) < 50) y -= speed;
                        state = smart;
                    }
                }

                if (canMove(x - speed, y)) {
                    if (randomGen.nextInt(100) < 50) x -= speed;
                }
            }
            else if (lastDir == up) {

                if (x < Game.getPlayer().myGetX()) {
                    if (canMove(x + speed, y)) {
                        if (randomGen.nextInt(100) < 50) x += speed;
                        state = smart;
                    }
                }
                else {
                    if (canMove(x - speed, y)) {
                        if (randomGen.nextInt(100) < 50) x -= speed;
                        state = smart;
                    }
                }

                if (canMove(x, y - speed)) {
                    if (randomGen.nextInt(100) < 50) y -= speed;
                }
            }
            else if (lastDir == down) {

                if (x < Game.getPlayer().myGetX()) {
                    if (canMove(x + speed, y)) {
                        if (randomGen.nextInt(100) < 50) x += speed;
                        state = smart;
                    }
                }
                else {
                    if (canMove(x - speed, y)) {
                        if (randomGen.nextInt(100) < 50) x -= speed;
                        state = smart;
                    }
                }

                if (canMove(x, y + speed)) {
                    if (randomGen.nextInt(100) < 50) y += speed;
                }
            }
            time++;
            if (time == targetTime) {
                state = random;
                time = 0;
            }
        }
    }

    private boolean canMove(final double nextX, final double nextY) {
        Rectangle bounds = new Rectangle(nextX + 1, nextY + 1, getWidth() - 2, getHeight() - 2);
        Map map = Game.getMap();

        for (int xx = 0; xx < map.getTiles().length; xx++) {
            for (int yy = 0; yy < map.getTiles()[0].length; yy++) {
                if (map.getTiles()[xx][yy] != null) {
                    if (bounds.intersects(map.getTiles()[xx][yy].getBoundsInLocal())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public double myGetX() {
        return x;
    }

    public double myGetY() {
        return y;
    }

}
