package ru.sbt.sandbox;

import java.awt.*;
import java.util.Objects;

public class Ball {
    private int positionX;
    private int positionY;

    Ball(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public synchronized int getPositionX() {
        return positionX;
    }

    public synchronized void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public synchronized int getPositionY() {
        return positionY;
    }

    public synchronized void setPositionY(int positionY) {
        this.positionY = positionY;
    }


    public boolean IsMyPosition(int x, int y) {
        return (getPositionX() == x) && (getPositionY() == y);
    }

    public Point CalcNewPosition(int direction) {
        int newX = getPositionX();
        int newY = getPositionY();

        switch (direction % 4) {
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

            case 3: default: {
                newX--;
                break;
            }
        }

        return new Point(newX, newY);
    }
}
