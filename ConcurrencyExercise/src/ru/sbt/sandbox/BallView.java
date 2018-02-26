package ru.sbt.sandbox;

import javafx.scene.shape.Circle;

public class BallView {
    private Ball ball;
    private Circle circle;

    public BallView(Ball ball, Circle circle) {
        this.ball = ball;
        this.circle = circle;
    }

    public synchronized void ShowBall() {
        circle.relocate(ball.getPositionX() * circle.getRadius()*2, ball.getPositionY() * circle.getRadius()*2);
    }
}
