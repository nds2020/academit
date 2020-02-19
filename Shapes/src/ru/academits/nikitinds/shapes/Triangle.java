package ru.academits.nikitinds.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double epsilon = 1.0e-10;

        if (Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) <= epsilon) {
            throw new IllegalArgumentException("точки с введенными координатами лежат на одной прямой, треугольник не получился");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x2, y2, x3, y3);
        double side3 = getSideLength(x3, y3, x1, y1);
        double semiPerimeter = (side1 + side2 + side3) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3));
    }

    @Override
    public double getPerimeter() {

        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x2, y2, x3, y3);
        double side3 = getSideLength(x3, y3, x1, y1);
        return side1 + side2 + side3;
    }

    private double getSideLength(double startX, double startY, double endX, double endY) {
        return Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
    }

    @Override
    public String toString() {
        return String.format("треугольник, у которого ширина %.2f, высота %.2f, периметр %.2f, площадь %.2f",
                getWidth(), getHeight(), getPerimeter(), getArea());
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
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

        Triangle p = (Triangle) o;
        return x1 == p.x1 && y1 == p.y1 && x2 == p.x2 && y2 == p.y2 && x3 == p.x3 && y3 == p.y3;
    }
}
