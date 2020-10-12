package ru.academit.nikitinds.model.converters;

import ru.academit.nikitinds.model.types.Type;

public interface Converter {
    void convert (Type initialTemperature, Type resultTemperature);
}