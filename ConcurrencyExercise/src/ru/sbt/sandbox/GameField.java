package ru.sbt.sandbox;

import java.awt.*;
import java.util.HashSet;
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


    private synchronized boolean isCoordInsideField(int x, int y) {
        return (x >= 0) && (x < sizeX) && (y >= 0) && (y < sizeY);
    }

    private boolean isCoordOccupied(int x, int y) {
        for (Ball ball: balls) {
            if ((ball.positionX == x) && (ball.positionY == y))
                return true;
        }
        return false;
    }

    private boolean isCoordOccupied(int x, int y, Ball selfBall) {
        for (Ball ball: balls) {
            if ((!ball.equals(selfBall)) && (ball.positionX == x) && (ball.positionY == y))
                return true;
        }
        return false;
    }


    public synchronized boolean AddBall() {
        int x;
        int y;
        int cyclingProtector = 0;

        do {
            x = (int) (Math.random() * sizeX);
            y = (int) (Math.random() * sizeY);
            cyclingProtector++;
        } while ((isCoordOccupied(x, y)) && (cyclingProtector <= 300));

        if (isCoordOccupied(x, y))
            return false;

        balls.add(new Ball(x, y));
        return true;
    }

    public synchronized void MoveBall(Ball ballToMove) {
        Point newPos;
        newPos = ballToMove.CalcNewPosition((int) (Math.random()*4));

        // Если позиция корректная - делаем ход
        if (CheckPosAndMakeMove(newPos.x, newPos.y, ballToMove))
            return;

        for (int i = 0; i < 4; i++) {
            newPos = ballToMove.CalcNewPosition(i);

            CheckPosAndMakeMove(newPos.x, newPos.y, ballToMove);
        }

    }

    private boolean CheckPosAndMakeMove(int x, int y, Ball ballToMove) {
        // Если позиция корректная - делаем ход
        if (isCoordInsideField(x, y) && !isCoordOccupied(x, y, ballToMove)) {
            ballToMove.positionX = x;
            ballToMove.positionY = y;

            return true;
        }
        return false;
    }

}
