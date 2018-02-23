package ru.sbt.sandbox;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BallView {
    Ball ball;
    Circle circle;

    public BallView(Ball ball, Circle circle) {
        this.ball = ball;
        this.circle = circle;
    }

    public void ShowBall() {
        circle.setCenterX(ball.positionX * circle.getRadius()*2 + circle.getRadius());
        circle.setCenterY(ball.positionY * circle.getRadius()*2 + circle.getRadius());
    }
}
