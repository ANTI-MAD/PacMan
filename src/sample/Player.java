package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Класс необходим для создания игрока и управление его движением.
 */

public class Player extends Rectangle{
    private double x, y;
    private boolean right, left, up, down;
    private int speed = 4;
    private int lifeCount = 3;
    private double x0, y0;

    public Player(){
        setX(x);
        setY(y);
        setWidth(32);
        setHeight(32);

    }

    public void tick(){
        if (right && canMove(x + speed, y)) x += speed;
        if (left && canMove(x - speed, y)) x -= speed;
        if (up && canMove(x, y - speed)) y -= speed;
        if (down && canMove(x, y + speed)) y += speed;

        Map map = Game.getMap();

        for (int i = 0; i < map.getApples().size(); i++) {

            // координаты игрока
            Rectangle bounds = new Rectangle(x - 1, y - 1, 32, 32);
            // если игрок прикоснулся к яблоку, то яблоко удаляется с поля
            if (bounds.intersects(map.getApples().get(i).getBoundsInLocal())) {
                map.getApples().remove(i);
                break;
            }
        }

        if (map.getApples().size() == 0 && !map.getBool()[4][4]) {
            //Game win
            // удаляем приведения
            while (map.getEnemies().size() != 0) {
                map.getEnemies().remove(0);
            }

            //начинаем игру заново
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    map.getBool()[i][j] = true;
                }
            }
        }

        for (int i = 0; i < map.getEnemies().size(); i++) {
            Enemy enemy = map.getEnemies().get(i);
            Rectangle bounds = new Rectangle(x, y, 32, 32);
            Rectangle bounds2 = new Rectangle(enemy.myGetX(), enemy.myGetY(), 32, 32);
            // проверка на то, прикоснулся ли игрок к приведению
            if (bounds.intersects(bounds2.getBoundsInLocal())) {
                lifeCount--;

                // возвращение приведений на их первоначальное положение (создание новых)
                for (int j = 0; j < map.getEnemies().size(); j++) {
                    enemy = map.getEnemies().get(j);
                    map.getBool()[(int) enemy.getX() / 32][(int) enemy.getY() / 32] = true;
                }

                // удаление старых приведений
                while (map.getEnemies().size() != map.getEnemies().size() / 2){
                    map.getEnemies().remove(0);
                }

                // установка игрока в первоначальное положение
                map.getBool()[(int) x0 / 32][(int) y0 / 32] = true;
                i = map.getEnemies().size();
            }
            if (lifeCount == 0) {
                System.exit(1);
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


    public void render(final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillRect(x, y, 32, 32);
    }

    public void setRight(final boolean bool){
        right = bool;
    }

    public void setLeft(final boolean bool){
        left = bool;
    }

    public void setUp(final boolean bool){
        up = bool;
    }

    public void setDown(final boolean bool) {
        down = bool;
    }

    public void mySetX(final double x) {
        this.x = x;
        x0 = x;
    }

    public void mySetY(final double y) {
        this.y = y;
        y0 = y;
    }

    public double myGetX() {
        return x;
    }

    public double myGetY() {
        return y;
    }
}
