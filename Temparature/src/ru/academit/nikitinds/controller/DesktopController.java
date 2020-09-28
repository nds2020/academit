package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.model.Converter;
import ru.academit.nikitinds.model.FahrenheitConverter;
import ru.academit.nikitinds.model.KelvinConverter;
import ru.academit.nikitinds.view.TemperatureTypes;
import ru.academit.nikitinds.view.View;

public class DesktopController implements Controller {
    private Converter converter;
    private View view;

    public DesktopController(Converter converter) {
        this.converter = converter;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void convert(double initialTemperature, String resultTemperatureType) {
        double result;

        if (resultTemperatureType.equals(TemperatureTypes.CELSIUS.getName())) {
            result = converter.convertToCelsius(initialTemperature);
        } else if (resultTemperatureType.equals(TemperatureTypes.KELVIN.getName())) {
            result = converter.convertToKelvin(initialTemperature);
        } else {
            result = converter.convertToFahrenheit(initialTemperature);
        }

        view.showResult(result);
    }

    @Override
    public void selectConverter(String initialTemperatureType) {
        if (initialTemperatureType.equals(TemperatureTypes.KELVIN.getName())) {
            converter = new KelvinConverter();
        } else if (initialTemperatureType.equals(TemperatureTypes.FAHRENHEIT.getName())) {
            converter = new FahrenheitConverter();
        }
    }
}