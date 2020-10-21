package ru.academit.nikitinds.model;

public interface TemperatureScale {
    double getValue();

    void setValue(double value);

    void convertToCelsius();

    void convertFromCelsius();
}