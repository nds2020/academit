package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Celsius;
import ru.academit.nikitinds.model.types.Fahrenheit;
import ru.academit.nikitinds.model.types.Type;

public class FahrenheitToCelsiusConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Fahrenheit initialTemp = (Fahrenheit) initialTemperature;
        Celsius resultTemp = (Celsius) resultTemperature;
        resultTemp.setValue((initialTemp.getValue() - 32) * 5 / 9);
    }
}