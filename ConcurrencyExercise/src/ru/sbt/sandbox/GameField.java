package ru.sbt.sandbox;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameField {
    int sizeX;
    int sizeY;
    Set<Ball> balls;

    public Set<Ball> getBalls() {
        return balls;
    }

    public GameField(int sizeX, int sizeY) {
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

    public void AddBall() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * sizeX);
            y = (int) (Math.random() * sizeY);
        } while (IsCoordOccupied(x, y));

        balls.add(new Ball(x, y));
    }

    public synchronized void MoveBall(int x, int y, Ball ballToMove) {
        ballToMove.positionX = x;
        ballToMove.positionY = y;

    }
}
