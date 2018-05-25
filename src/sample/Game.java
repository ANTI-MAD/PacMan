package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Класс для начала игры и обработки информации.
 */

public class Game extends Canvas implements Runnable{

    public static final double WIDTH = 640, HEIGHT = 480;
    private boolean isRunning = false;
    private Thread thread;
    private Stage theStage;
    private Group root = new Group();
    private Canvas canvas = new Canvas(WIDTH, HEIGHT);
    private Scene scene = new Scene(root, WIDTH, HEIGHT);

    private static Player player;
    private static Map map;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {

            GraphicsContext g = canvas.getGraphicsContext2D();
            g.setFill(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            player.render(g);
            map.render(g);

        }
    };

    public Game(final Stage primaryStage) {
        theStage = primaryStage;

        map = new Map();
        player = new Player();

    }

    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;

        thread = new Thread(this::run);
        thread.start();
    }

    public synchronized void stop() {

        if (!isRunning) return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double targetTick = 60;
        double delta = 0;
        double ns = 1000000000 / targetTick;
        int fps = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;


            while (delta >= 1) {
                tick();
                render();
                fps++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }
        }
        animationTimer.stop();
        stop();
    }

    private void tick() {
        player.tick();
        map.tick();
    }

    private void render() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: player.setUp(true); break;
                    case DOWN: player.setDown(true); break;
                    case LEFT: player.setLeft(true); break;
                    case RIGHT: player.setRight(true); break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: player.setUp(false); break;
                    case DOWN: player.setDown(false); break;
                    case LEFT: player.setLeft(false); break;
                    case RIGHT: player.setRight(false); break;
                }
            }
        });

        animationTimer.start();
    }

    public void startGame() {
        theStage.setTitle("PACMAN-Game");
        theStage.setScene(scene);
        root.getChildren().addAll(canvas);
        theStage.show();
        start();
    }

    public static Player getPlayer(){
        return player;
    }

    public static Map getMap() {
        return map;
    }
}
