package ru.academits.nikitinds.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("радиус окружности должен быть больше нуля");
        }

        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("окружность, у которой радиус %.2f, диаметр %.2f, длина %.2f, площадь %.2f",
                radius, getHeight(), getPerimeter(), getArea());
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        return prime * hash + Double.hashCode(radius);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Circle p = (Circle) o;
        return radius == p.radius;
    }
}