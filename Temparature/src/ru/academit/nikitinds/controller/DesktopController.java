package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.model.converters.*;
import ru.academit.nikitinds.model.types.Celsius;
import ru.academit.nikitinds.model.types.Fahrenheit;
import ru.academit.nikitinds.model.types.Kelvin;
import ru.academit.nikitinds.model.types.Type;
import ru.academit.nikitinds.view.View;

public class DesktopController implements Controller {
    private View view;

    @Override
    public Type[] getTypes() {
        return new Type[]{new Celsius(), new Fahrenheit(), new Kelvin()};
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void convert(double value, Type initialTemp, Type resultTemp) {
        initialTemp.setValue(value);

        Converter[] converters = {
                new CelsiusToFahrenheitConverter(), new CelsiusToKelvinConverter(),
                new FahrenheitToCelsiusConverter(), new FahrenheitToKelvinConverter(),
                new KelvinToCelsiusConverter(), new KelvinToFahrenheitConverter()};

        for (Converter converter : converters) {
            try {
                converter.convert(initialTemp, resultTemp);
                break;
            } catch (ClassCastException ignored) {
            }
        }

        view.showResult(resultTemp);
    }
}