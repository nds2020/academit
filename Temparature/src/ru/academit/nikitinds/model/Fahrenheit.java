package ru.academit.nikitinds.model;

public class Fahrenheit implements TemperatureScale {
    private static final String NAME = "градусы Фаренгейта";
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
        value = (value - 32) * 5 / 9;
    }

    @Override
    public void convertFromCelsius() {
        value = value * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return NAME;
    }
}