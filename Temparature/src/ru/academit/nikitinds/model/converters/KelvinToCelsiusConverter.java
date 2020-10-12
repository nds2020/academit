package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Celsius;
import ru.academit.nikitinds.model.types.Kelvin;
import ru.academit.nikitinds.model.types.Type;

public class KelvinToCelsiusConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Kelvin initialTemp = (Kelvin) initialTemperature;
        Celsius resultTemp = (Celsius) resultTemperature;
        resultTemp.setValue(initialTemp.getValue() - 273.1);
    }
}