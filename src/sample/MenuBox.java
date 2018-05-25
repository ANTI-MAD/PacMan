package sample;

import javafx.scene.layout.Pane;

/**
 * Класс хранит пункты меню.
 */
public class MenuBox extends Pane {
    private static SubMenu subMenu;
    public MenuBox(final SubMenu subMenu) {
        MenuBox.subMenu = subMenu;

        getChildren().addAll(subMenu);
    }

    /**
     * Получение пунктов меню, хранящихся в классе SubMenu
     * @param subMenu
     */
    public void setSubMenu(final SubMenu subMenu) {
        getChildren().remove(MenuBox.subMenu);
        MenuBox.subMenu = subMenu;
        getChildren().add(MenuBox.subMenu);
    }
}
