package ru.academit.nikitinds.controller;

import ru.academit.nikitinds.model.types.Type;
import ru.academit.nikitinds.view.View;

public interface Controller {
    Type[] getTypes();

    void setView(View view);

    void convert(double value, Type initialTemp, Type resultTemp);
}