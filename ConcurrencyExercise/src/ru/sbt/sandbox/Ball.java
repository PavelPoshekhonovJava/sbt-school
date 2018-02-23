package ru.sbt.sandbox;

import java.awt.*;

public class Ball {
    int positionX;
    int positionY;

    Ball(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Point CalcNewPosition(int direction) {
        int newX = positionX;
        int newY = positionY;

        switch (direction) {
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
