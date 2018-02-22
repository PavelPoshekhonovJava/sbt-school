package ru.sbt.sandbox;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class BallGame extends Application {
    // Model
    public static final int BALLCOUNT = 5;
    public static final int SIZEX = 20;
    public static final int SIZEY = 15;

    public GameField gameField;

    //View
    public static final int BALLSIZE = 30;
    public static final long VIEWSLEEP = 10;
    public List<BallView> ballViews;

    // Controller
    public List<Thread> threads;


    public void CreateGame(){
        // Создаем игровое поле
        gameField = new GameField(SIZEX, SIZEY);

        // Добавляем шары
        for (int i = 0; i < BALLCOUNT; i++){
            gameField.AddBall();
        }

        // Создаем поток для каждого шара
        threads = new ArrayList<>();

        for (Ball ball: gameField.getBalls()) {
            threads.add(new Thread(new Player( ball, gameField, (long) (Math.random()*1000) )));
        }
    }

    public void RunGame(){
        // Запускаем потоки
        for (Thread thread: threads) {
            thread.start();
        }
    }

    public void StopGame(){
        // Запускаем потоки
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }

    public void CreateView(Stage primaryStage){
        Group root = new Group();

        // Создаем отображение сцены
        Scene scene = new Scene(root, gameField.sizeX * BALLSIZE, gameField.sizeY * BALLSIZE, Color.BLACK);

        // Создаем отображение шаров
        ballViews = new ArrayList<>();
        for (Ball ball: gameField.getBalls()) {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
            ballViews.add(new BallView(BALLSIZE, color, ball));
        }

        root.getChildren().addAll(ballViews);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void PrintBalls(){
        for (BallView ballView: ballViews) {
            ballView.setCenterX(ballView.ball.positionX * ballView.getRadius());
            ballView.setCenterY(ballView.ball.positionY * ballView.getRadius());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        CreateGame();
        CreateView(primaryStage);
        PrintBalls();

        RunGame();

        Timer timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                PrintBalls();
            }
        };
        timer.schedule(timerTask, 0, VIEWSLEEP);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                StopGame();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
