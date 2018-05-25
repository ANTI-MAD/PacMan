package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Класс создаёт меню.
 */
public class Menu {
    public Menu(final Stage primaryStage) {
        Pane root = new Pane();
        startPage(root);

        MenuItem game = new MenuItem("Игра");
        MenuItem options = new MenuItem("Управление");
        MenuItem exitGame = new MenuItem("Выход");
        MenuItem optionsBack = new MenuItem("Назад");

        SubMenu mainMenu = new SubMenu(game, options, exitGame);
        SubMenu optionsMenu = new SubMenu(optionsBack);

        MenuItem newGame = new MenuItem("Новая игра");
        MenuItem loadGame = new MenuItem("Загрузить игру");
        MenuItem gameBack = new MenuItem("Назад");

        SubMenu gameMenu = new SubMenu(newGame, loadGame, gameBack);

        MenuBox menuBox = new MenuBox(mainMenu);

        Game game1 = new Game(primaryStage);

        game.setOnMouseClicked(event -> menuBox.setSubMenu(gameMenu));
        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));
        optionsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        gameBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));
        newGame.setOnMouseClicked(event -> game1.startGame());
        root.getChildren().addAll(menuBox);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("PACMAN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Метод загружает и устанавливает размер картинки(заставки).
     * @param root
     */
    private void startPage(final Pane root) {
        Image image = new Image(getClass().getResourceAsStream("pacman-game.jpg"));
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(900);
        root.getChildren().add(img);
    }
}
