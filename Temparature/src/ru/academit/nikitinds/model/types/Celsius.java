package ru.academit.nikitinds.model.types;

public class Celsius implements Type {
    private static final String name = "градусы Цельсия";
    private double value;

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}