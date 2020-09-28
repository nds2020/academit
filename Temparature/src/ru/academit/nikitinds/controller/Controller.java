package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.view.View;

public interface Controller {
    void setView(View view);

    void convert(double initialTemperature, String resultTemperatureType);

    void selectConverter(String name);
}