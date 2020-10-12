package ru.academit.nikitinds.main;

import ru.academit.nikitinds.controller.Controller;
import ru.academit.nikitinds.controller.DesktopController;
import ru.academit.nikitinds.model.CelsiusConverter;
import ru.academit.nikitinds.model.Converter;
import ru.academit.nikitinds.view.DesktopView;
import ru.academit.nikitinds.view.View;

public class Main {
    public static void main(String[] args) {
        Converter converter = new CelsiusConverter();
        Controller controller = new DesktopController(converter);

        View view = new DesktopView(controller);
        controller.setView(view);

        view.start();
    }
}