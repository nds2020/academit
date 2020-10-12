package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Fahrenheit;
import ru.academit.nikitinds.model.types.Kelvin;
import ru.academit.nikitinds.model.types.Type;

public class FahrenheitToKelvinConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Fahrenheit initialTemp = (Fahrenheit) initialTemperature;
        Kelvin resultTemp = (Kelvin) resultTemperature;
        resultTemp.setValue((initialTemp.getValue() - 32) * 5 / 9 + 273.15);
    }
}