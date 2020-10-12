package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Celsius;
import ru.academit.nikitinds.model.types.Kelvin;
import ru.academit.nikitinds.model.types.Type;

public class CelsiusToKelvinConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Celsius initialTemp = (Celsius) initialTemperature;
        Kelvin resultTemp = (Kelvin) resultTemperature;
        resultTemp.setValue(initialTemp.getValue() + 273.15);
    }
}