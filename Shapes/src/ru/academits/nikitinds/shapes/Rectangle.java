package ru.academits.nikitinds.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("ширина и длина прямоугольника должны быть больше нуля");
        }

        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return width * 2 + height * 2;
    }

    @Override
    public String toString() {
        return String.format("прямоугольник, у которого высота %.2f, ширина %.2f, периметр %.2f, площадь %.2f",
                height, width, getPerimeter(), getArea());
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle p = (Rectangle) o;
        return width == p.width && height == p.height;
    }
}