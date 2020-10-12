package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Fahrenheit;
import ru.academit.nikitinds.model.types.Kelvin;
import ru.academit.nikitinds.model.types.Type;

public class KelvinToFahrenheitConverter implements Converter {
    public void convert(Type initialTemperature, Type resultTemperature) {
        Kelvin initialTemp = (Kelvin) initialTemperature;
        Fahrenheit resultTemp = (Fahrenheit) resultTemperature;
        resultTemp.setValue((initialTemp.getValue() - 273.15) * 9 / 5 + 32);
    }
}