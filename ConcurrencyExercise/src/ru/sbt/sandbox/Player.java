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

        int randomDirection;
        int newX;
        int newY;

        while(true) {
            // Спим
            try {
                Thread.sleep(timeToSleepMs);
            } catch (InterruptedException e) {
                break;
            }

            // Определяем новую позицию
            randomDirection = (int) (Math.random()*4);
            newX = ball.positionX;
            newY = ball.positionY;

            switch (randomDirection) {
                case 0: {
                    newY++;
                    break;
                }

                case 1: {
                    newX++;
                    break;
                }

                case 2: {
                    newY--;
                    break;
                }

                case 3: {
                    newX--;
                    break;
                }

            }

            // Если позиция корректная - делаем ход
            if (field.IsCoordInsideField(newX, newY) && !field.IsCoordOccupied(newX, newY, ball)) {
                field.MoveBall(newX, newY, ball);
            }
        }

    }

}
