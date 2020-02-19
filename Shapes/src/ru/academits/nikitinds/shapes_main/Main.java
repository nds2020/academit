package ru.academits.nikitinds.shapes_main;

import ru.academits.nikitinds.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        Comparator<Shape> comparator = new ShapesAreaComparator();

        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithPreMaxPerimeter(Shape[] shapes) {
        Comparator<Shape> comparator = new ShapesPerimeterComparator();

        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        try {
            Shape[] shapes = {new Circle(3), new Square(5), new Triangle(0, 0, 2, 2, 1, 3),
                    new Rectangle(3, 4), new Square(3), new Circle(6), new Square(4)};

            System.out.println("Фигура с максимальной площадью - это " + getShapeWithMaxArea(shapes));

            System.out.println();

            System.out.println("Фигура со вторым по величине периметром - это " + getShapeWithPreMaxPerimeter(shapes));

        } catch (IllegalArgumentException e) {
            System.out.println("Введены некорректные данные: " + e.getLocalizedMessage());
        }
    }
}