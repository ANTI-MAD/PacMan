package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Класс раставляет стену, за которую не может выйти игрок.
 */
public class Tile extends Rectangle {
    private double x, y;

    public Tile(final double x, final double y) {
        setX(x);
        setY(y);
        setWidth(32);
        setHeight(32);
        this.x = x;
        this.y = y;
    }

    public void render(final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.rgb(33, 0, 127));
        graphicsContext.fillRect(x, y, 32, 32);
    }

}
