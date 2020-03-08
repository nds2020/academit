package ru.academits.nikitinds.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность вектора должна быть больше нуля");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        int lastIndex = components.length - 1;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < lastIndex; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.append(components[lastIndex]).append("}");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        int vectorSize = vector.getSize();

        if (components.length < vectorSize) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int vectorSize = vector.getSize();

        if (components.length < vectorSize) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        if (scalar == 1) {
            return;
        }

        for (int i = 0; i < components.length; i++) {
            if (components[i] == 0) { // оставил это условие, чтобы при умножении на -1 не получался -0
                continue;
            }

            components[i] *= scalar;
        }
    }

    public void revert() {
        multiplyByScalar(-1);
    }

    public double getModule() {
        double module = 0;

        for (double component : components) {
            module += Math.pow(component, 2);
        }

        return Math.sqrt(module);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("индексом компоненты должно быть неотрицательное число меньше рамера вектора");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("индексом компоненты должно быть неотрицательное число меньше рамера вектора");
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) object;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sum = new Vector(vector1);

        sum.add(vector2);
        return sum;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector difference = new Vector(vector1);

        difference.subtract(vector2);
        return difference;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}