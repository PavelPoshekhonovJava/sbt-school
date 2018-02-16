////////////////////////////////////////////////////////////
// MVC. Бизнес уровень
// Базовый класс двигающегося игрового объекта
////////////////////////////////////////////////////////////

package ru.sbt.sandbox;

import java.awt.*;

public class MovingObject extends GameObject {
    // Направление
    Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Конструктор
    public MovingObject(String name, Point location) {
        this(name, location, Direction.Bottom);
    }

    public MovingObject(String name, Point location, Direction direction) {
        super(name, location);
        this.direction = direction;
    }


}
