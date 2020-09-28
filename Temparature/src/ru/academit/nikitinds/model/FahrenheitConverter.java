package ru.academit.nikitinds.model;

public class FahrenheitConverter implements Converter {
    @Override
    public double convertToKelvin(double initialTemperature) {
        return (initialTemperature - 32) * 5 / 9 + 273.15;
    }

    @Override
    public double convertToFahrenheit(double initialTemperature) {
        return initialTemperature;
    }

    @Override
    public double convertToCelsius(double initialTemperature) {
        return (initialTemperature - 32) * 5 / 9;
    }
}