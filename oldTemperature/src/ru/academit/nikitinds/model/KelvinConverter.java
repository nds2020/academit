package ru.academit.nikitinds.model;

public class KelvinConverter implements Converter {
    @Override
    public double convertToKelvin(double initialTemperature) {
        return initialTemperature;
    }

    @Override
    public double convertToFahrenheit(double initialTemperature) {
        return (initialTemperature - 273.15) * 9 / 5 + 32;
    }

    @Override
    public double convertToCelsius(double initialTemperature) {
        return initialTemperature - 273.15;
    }
}