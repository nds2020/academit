package ru.academits.nikitinds;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность вектора должна быть больше нуля");
        }

        this.size = size;
        components = new double[size];
    }

    public Vector(Vector vector) {
        size = vector.size;
        components = Arrays.copyOf(vector.components, size);
    }

    public Vector(double[] components) {
        this(components.length);
        System.arraycopy(components, 0, this.components, 0, size);

    }

    public Vector(int size, double[] components) {
        this(size);
        System.arraycopy(components, 0, this.components, 0, Math.min(components.length, size));
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (double component : components) {
            string.append(component).append(", ");
        }

        return "{" + string + "\b\b}";
    }

    public void add(Vector vector) {
        if (size >= vector.size) {
            for (int i = 0; i < vector.size; i++) {
                components[i] += vector.components[i];
            }
        } else {
            size = vector.size;
            double[] temp = Arrays.copyOf(components, size);

            for (int i = 0; i < size; i++) {
                temp[i] += vector.components[i];
            }

            components = Arrays.copyOf(temp, temp.length);
        }
    }

    public void subtract(Vector vector) {
        if (size >= vector.size) {
            for (int i = 0; i < vector.size; i++) {
                components[i] -= vector.components[i];
            }
        } else {
            size = vector.size;
            double[] temp = Arrays.copyOf(components, size);

            for (int i = 0; i < size; i++) {
                temp[i] -= vector.components[i];
            }

            components = Arrays.copyOf(temp, temp.length);
        }
    }

    public void multiplyByScalar(double scalar) {
        if (scalar == 1) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (components[i] == 0) {
                continue;
            }

            components[i] *= scalar;
        }
    }

    public void revert() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double length = 0;

        for (double component : components) {
            length += Math.pow(component, 2);
        }

        return Math.sqrt(length);
    }

    public double getComponent(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("не существует компоненты с указанным индексом, индекс компоненты должен быть меньше размерности вектора");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index >= size) {
            throw new IllegalArgumentException("не существует компоненты с указанным индексом, индекс компоненты должен быть меньше размерности вектора");
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
        int prime = 7;
        int hash = 1;
        hash = prime * hash + Double.hashCode(size);
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
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

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double scalarMultiplication = 0;

        for (int i = 0; i < (Math.min(vector1.size, vector2.size)); i++) {
            scalarMultiplication += vector1.components[i] * vector2.components[i];
        }

        return scalarMultiplication;
    }
}