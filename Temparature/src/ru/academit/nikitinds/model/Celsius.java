package ru.academit.nikitinds.model;

public class Celsius implements TemperatureScale {
    private static final String NAME = "градусы Цельсия";
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
    public void convertToCelsius() {
    }

    @Override
    public void convertFromCelsius() {
    }

    @Override
    public String toString() {
        return NAME;
    }
}