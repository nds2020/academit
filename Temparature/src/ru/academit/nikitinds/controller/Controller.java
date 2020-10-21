package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.model.TemperatureScale;
import ru.academit.nikitinds.view.View;

public interface Controller {
    TemperatureScale[] getScales();

    void setView(View view);

    void convert(double value, TemperatureScale initialScale, TemperatureScale resultScale);
}