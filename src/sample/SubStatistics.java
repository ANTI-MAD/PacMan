package sample;

import javafx.scene.layout.VBox;

public class SubStatistics extends VBox {

    public SubStatistics(final Statistics...items) {
        setSpacing(20);
        setTranslateX(700);
        setTranslateY(700);
        for (Statistics item : items) {
            getChildren().addAll(item);
        }
    }
}
