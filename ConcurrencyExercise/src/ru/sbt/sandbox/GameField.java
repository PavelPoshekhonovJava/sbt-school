package ru.sbt.sandbox;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameField {
    int sizeX;
    int sizeY;
    Set<Ball> balls;

    GameField(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        balls = new HashSet<>();
    }


    public synchronized boolean IsCoordInsideField(int x, int y) {
        if ((x < 0) || (x >= sizeX) || (y < 0) || (y >= sizeY))
            return false;
        else
            return true;
    }

    public boolean IsCoordOccupied(int x, int y) {
        for (Ball ball: balls) {
            if ((ball.positionX == x) && (ball.positionY == y))
                return true;
        }
        return false;
    }

    public synchronized boolean IsCoordOccupied(int x, int y, Ball selfBall) {
        for (Ball ball: balls) {
            if ((!ball.equals(selfBall)) && (ball.positionX == x) && (ball.positionY == y))
                return true;
        }
        return false;
    }


    public boolean AddBall() {
        int x;
        int y;
        int cyclingProtector = 0;

        do {
            x = (int) (Math.random() * sizeX);
            y = (int) (Math.random() * sizeY);
            cyclingProtector++;
        } while ((IsCoordOccupied(x, y)) && (cyclingProtector <= 300));

        if (IsCoordOccupied(x, y))
            return false;

        balls.add(new Ball(x, y));
        return true;
    }

    public synchronized void MoveBall(Ball ballToMove) {
        int randomDirection;
        int newX;
        int newY;

        // Определяем новую позицию
        randomDirection = (int) (Math.random()*4);
        newX = ballToMove.positionX;
        newY = ballToMove.positionY;

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
        if (IsCoordInsideField(newX, newY) && !IsCoordOccupied(newX, newY, ballToMove)) {
            ballToMove.positionX = newX;
            ballToMove.positionY = newY;
        }


    }
}
