////////////////////////////////////////////////////////////
// MVC. Бизнес уровень
// Базовый класс статического игрового объекта
////////////////////////////////////////////////////////////

package ru.sbt.sandbox;

import java.awt.*;

public class GameObject {
    // Название для отображения
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Положение объекта
    Point location;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    // Конструктор
    public GameObject(String name, Point location) {
        this.name = name;
        this.location = location;
    }

    // Проверка на пересечение с объектом
    public boolean CheckCrossing(Point loc)
    {
        if ((location.x == loc.x) && (location.y == loc.y))
            return true;
        else
            return false;
    }
}
