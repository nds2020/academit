package ru.academits.nikitinds.shapes;

import java.util.Comparator;

public class ShapesPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) (shape1.getPerimeter() - shape2.getPerimeter());
    }
}