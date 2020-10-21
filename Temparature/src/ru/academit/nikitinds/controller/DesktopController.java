package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.model.TemperatureScale;
import ru.academit.nikitinds.model.Celsius;
import ru.academit.nikitinds.model.Fahrenheit;
import ru.academit.nikitinds.model.Kelvin;
import ru.academit.nikitinds.view.View;

public class DesktopController implements Controller {
    private View view;

    @Override
    public TemperatureScale[] getScales() {
        return new TemperatureScale[]{new Celsius(), new Fahrenheit(), new Kelvin()};
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void convert(double value, TemperatureScale initialScale, TemperatureScale resultScale) {
        initialScale.setValue(value);
        initialScale.convertToCelsius();

        resultScale.setValue(initialScale.getValue());
        resultScale.convertFromCelsius();

        view.showResult(resultScale.getValue());
    }
}