package ru.academit.nikitinds.model.types;

public class Kelvin implements Type {
    private static final String name = "градусы Кельвина";
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