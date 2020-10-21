package ru.academit.nikitinds.model;

public class Kelvin implements TemperatureScale {
    private static final String NAME = "градусы Кельвина";
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
        value -= 273.15;
    }

    @Override
    public void convertFromCelsius() {
        value += 273.15;
    }

    @Override
    public String toString() {
        return NAME;
    }
}