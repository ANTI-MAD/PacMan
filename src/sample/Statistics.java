package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Statistics extends StackPane {

    public Statistics(final String name) {
        Text text = new Text(name);
        text.setFill(Color.YELLOW);
        text.setFont(Font.font("Segoe Print", FontWeight.BOLD, 15));
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(text);
    }
}
