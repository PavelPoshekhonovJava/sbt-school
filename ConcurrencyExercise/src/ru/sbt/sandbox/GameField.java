package ru.sbt.sandbox;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameField {
    int sizeX;
    int sizeY;
    volatile Set<Ball> balls;

    GameField(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        balls = new HashSet<>();
    }


    private boolean isPositionInsideField(int x, int y) {
        return (x >= 0) && (x < sizeX) && (y >= 0) && (y < sizeY);
    }

    private boolean isPositionOccupied(int x, int y) {
        for (Ball ball: balls) {
            if (ball.IsMyPosition(x, y))
                return true;
        }
        return false;
    }


    public synchronized void AddBall() {
        int x;
        int y;
        int cyclingProtector = 0;

        do {
            x = (int) (Math.random() * sizeX);
            y = (int) (Math.random() * sizeY);
            cyclingProtector++;
        } while ((isPositionOccupied(x, y)) && (cyclingProtector <= 300));

        if (!isPositionOccupied(x, y)) {
            balls.add(new Ball(x, y));
        }
    }

    public synchronized void MoveBall(Ball ballToMove) {
        Point newPos;
        int rndDirection;

        // Сначала пробуем переместить в случайном направлении
        rndDirection = (int) (Math.random()*4);
        newPos = ballToMove.CalcNewPosition(rndDirection);

        // Если позиция корректная - делаем ход
        if (CheckPosAndMakeMove(newPos.x, newPos.y, ballToMove))
            return;

        // Если в случайном направлении не переместились - перебираем все направления
        for (int i = rndDirection + 1; i < rndDirection + 4; i++) {
            newPos = ballToMove.CalcNewPosition(i);

            if (CheckPosAndMakeMove(newPos.x, newPos.y, ballToMove))
                return;
        }
    }

    private boolean CheckPosAndMakeMove(int x, int y, Ball ballToMove) {
        // Если позиция корректная - делаем ход
        if (isPositionInsideField(x, y) && !isPositionOccupied(x, y)) {
            ballToMove.setPositionX(x);
            ballToMove.setPositionY(y);

            return true;
        }
        return false;
    }

}
