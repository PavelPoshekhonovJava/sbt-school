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
    private static final int BALLCOUNT = 7;
    static final int SIZEX = 5;
    static final int SIZEY = 5;
    private GameField gameField;

    // View
    static final int BALLRADIUS = 30;
    private static final long VIEWSLEEP = 500;
    private List<BallView> ballViews;

    // Controller
    private static final int BALLSPEEDLO = 500;
    private static final int BALLSPEEDHI = 50;
    private List<Thread> threads;


    private void CreateGame(){
        // Создаем игровое поле
        gameField = new GameField(SIZEX, SIZEY);

        // Добавляем шары на игровое поле
        for (int i = 0; i < BALLCOUNT; i++){
            gameField.AddBall();
        }

        // Создаем поток для каждого шара
        threads = new ArrayList<>();
        for (Ball ball: gameField.balls) {
            long timeToSleepMs = (long) (BALLSPEEDHI + Math.random()* (BALLSPEEDLO - BALLSPEEDHI));
            threads.add(new Thread(new Player( ball, gameField, timeToSleepMs )));
        }
    }

    private void RunGame(){
        // Запускаем потоки
        for (Thread thread: threads) {
            thread.start();
        }
    }

    private void StopGame() {
        // Запускаем потоки
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }


    private void CreateView(Stage primaryStage){
        Group root = new Group();

        // Создаем игровое поле
        Scene scene = new Scene(root, gameField.sizeX * BALLRADIUS*2, gameField.sizeY * BALLRADIUS*2, Color.BLACK);

        // Создаем отображение шаров
        ballViews = new ArrayList<>();
        for (Ball ball: gameField.balls) {
            Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
            Circle circle = new Circle(BALLRADIUS, color);

            ballViews.add(new BallView(ball, circle));
            root.getChildren().add(circle);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void PrintBalls(){
        // Отображаем все шары
        for (BallView ballView: ballViews) {
            ballView.ShowBall();
        }
        System.out.println();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        CreateGame();

        CreateView(primaryStage);
        PrintBalls();

        RunGame();

        // Запускаем таймер отображения
        Timer timerView = new java.util.Timer();
        TimerTask timerViewTask = new TimerTask() {
            @Override
            public void run() {
                PrintBalls();
            }
        };
        timerView.schedule(timerViewTask, 0, VIEWSLEEP);


        // Закрываем потоки при закрытии программы
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
