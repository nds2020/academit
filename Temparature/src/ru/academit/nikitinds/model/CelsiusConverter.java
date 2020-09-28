package ru.academit.nikitinds.model;

public class CelsiusConverter implements Converter {

    @Override
    public double convertToKelvin(double initialTemperature) {
        return initialTemperature + 273.15;
    }

    @Override
    public double convertToFahrenheit(double initialTemperature) {
        return initialTemperature * 9 / 5 + 32;
    }

    @Override
    public double convertToCelsius(double initialTemperature) {
        return initialTemperature;
    }
}