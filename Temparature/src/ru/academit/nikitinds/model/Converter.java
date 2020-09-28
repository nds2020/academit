package ru.academit.nikitinds.model;

public interface Converter {
    double convertToKelvin(double initialTemperature);

    double convertToFahrenheit(double initialTemperature);

    double convertToCelsius(double initialTemperature);
}