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

    public Vector addVector(Vector vector) {
        Vector resultVector = new Vector(Math.max(size, vector.size), components);

        for (int i = 0; i < vector.size; i++) {
            resultVector.components[i] += vector.components[i];
        }

        return resultVector;
    }

    public Vector subtractVector(Vector vector) {
        Vector resultVector = new Vector(Math.max(size, vector.size), components);

        for (int i = 0; i < vector.size; i++) {
            resultVector.components[i] -= vector.components[i];
        }

        return resultVector;
    }

    public Vector multiplyByScalar(double scalar) {
        if (scalar == 0) {
            return new Vector(size);
        }

        if (scalar == 1) {
            return new Vector(this);
        }

        Vector resultVector = new Vector(this);

        for (int i = 0; i < resultVector.size; i++) {
            resultVector.components[i] *= scalar;
        }

        return resultVector;
    }

    public Vector revert() {
        return multiplyByScalar(-1);
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
        return size == vector.size && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int prime = 7;
        int hash = 1;
        hash = prime * hash + Double.hashCode(size);
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.size, vector2.size), vector1.components);

        for (int i = 0; i < vector2.size; i++) {
            resultVector.components[i] += vector2.components[i];
        }

        return resultVector;
    }

    public static Vector getVectorsDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.size, vector2.size), vector1.components);

        for (int i = 0; i < vector2.size; i++) {
            resultVector.components[i] -= vector2.components[i];
        }

        return resultVector;
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double scalarMultiplication = 0;

        for (int i = 0; i < (Math.min(vector1.size, vector2.size)); i++) {
            scalarMultiplication += vector1.components[i] * vector2.components[i];
        }

        return scalarMultiplication;
    }
}