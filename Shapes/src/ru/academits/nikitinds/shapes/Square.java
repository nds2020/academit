package ru.academits.nikitinds.shapes;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("сторона квадрата должна быть больше нуля");
        }

        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return side * 4;
    }

    @Override
    public String toString() {
        return String.format("квадрат, у которого сторона %.2f, периметр %.2f, площадь %.2f",
                side, getPerimeter(), getArea());
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        return prime * hash + Double.hashCode(side);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square p = (Square) o;
        return side == p.side;
    }
}