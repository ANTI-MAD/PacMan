package sample;

import javafx.scene.layout.VBox;

/**
 * Класс раставляет пункты меню.
 */
public class SubMenu extends VBox {
    /**
     * Метод расставляет пункты меню.
     * @param items - пункты меню
     */
    public SubMenu(final MenuItem...items) {
        setSpacing(20);
        setTranslateX(350);
        setTranslateY(380);
        for (MenuItem item : items) {
            getChildren().addAll(item);
        }
    }
}
