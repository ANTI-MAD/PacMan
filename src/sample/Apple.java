package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Класс расстваляет яблоки на поле.
 */
public class Apple extends Rectangle {
    private double x;
    private double y;

    public Apple(final double x, final double y) {
        setX(x + 10);
        setY(y + 10);
        setHeight(8);
        setWidth(8);
        this.x = x;
        this.y = y;
    }

    public void render(final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(x + 10, y + 10, 8, 8);
    }
}
