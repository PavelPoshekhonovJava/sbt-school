package ru.sbt.sandbox;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BallView extends Circle {
    Ball ball;

    public BallView(double radius, Paint fill, Ball ball) {
        super(radius, fill);
        this.ball = ball;
    }
}
