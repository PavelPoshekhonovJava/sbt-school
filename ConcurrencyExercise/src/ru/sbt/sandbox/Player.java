package ru.sbt.sandbox;

public class Player implements Runnable {
    Ball ball;
    GameField field;

    long timeToSleepMs;

    public Player(Ball ball, GameField field, long timeToSleepMs) {
        this.ball = ball;
        this.field = field;
        this.timeToSleepMs = timeToSleepMs;
    }


    @Override
    public void run() {
        while(!Thread.interrupted()) {
            // Перемещаем шар
            field.MoveBall(ball);

            // Спим
            try {
                Thread.sleep(timeToSleepMs);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
