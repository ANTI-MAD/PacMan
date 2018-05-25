package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Menu menu = new Menu(primaryStage);
        //Game game = new Game();
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
