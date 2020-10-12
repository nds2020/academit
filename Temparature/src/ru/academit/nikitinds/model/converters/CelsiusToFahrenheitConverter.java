package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Celsius;
import ru.academit.nikitinds.model.types.Fahrenheit;
import ru.academit.nikitinds.model.types.Type;

public class CelsiusToFahrenheitConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Celsius initialTemp = (Celsius) initialTemperature;
        Fahrenheit resultTemp = (Fahrenheit) resultTemperature;
        resultTemp.setValue(initialTemp.getValue() * 9 / 5 + 32);
    }
}