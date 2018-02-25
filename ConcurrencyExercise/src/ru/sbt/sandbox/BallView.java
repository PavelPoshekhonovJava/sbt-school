package ru.sbt.sandbox;

import javafx.scene.shape.Circle;

public class BallView {
    Ball ball;
    Circle circle;

    public BallView(Ball ball, Circle circle) {
        this.ball = ball;
        this.circle = circle;
    }

    public synchronized void ShowBall() {
        circle.setCenterX(ball.getPositionX() * circle.getRadius()*2 + circle.getRadius());
        circle.setCenterY(ball.getPositionY() * circle.getRadius()*2 + circle.getRadius());

        System.out.println(ball + " Отображение: " + circle);
    }
}
