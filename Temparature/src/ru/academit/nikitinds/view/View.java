package ru.academit.nikitinds.view;

import ru.academit.nikitinds.model.types.Type;

public interface View {
    void start();

    void showResult(Type temperature);
}