package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Класс создаёт пункты меню со свойством
 * <b>name</b>.
 */
public class MenuItem extends StackPane {
    /**
     * Метод создаёт пункты меню.
     * @param name - имя пункта меню
     */
    public MenuItem(final String name) {
        Text text = new Text(name);
        text.setFill(Color.YELLOW);
        text.setFont(Font.font("Segoe Print", FontWeight.BOLD, 30));
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(text);

        setOnMouseEntered(event -> {
            text.setFill(Color.RED);
            text.setFont(Font.font("Segoe Print", FontWeight.BOLD, 40));
        });
        setOnMouseExited(event -> {
            text.setFill(Color.YELLOW);
            text.setFont(Font.font("Segoe Print", FontWeight.BOLD, 30));
        });
    }
}
