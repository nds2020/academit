package ru.academits.nikitinds.shapes_main;

import ru.academits.nikitinds.shapes.Shape;
import java.util.Comparator;

public class ShapesAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}