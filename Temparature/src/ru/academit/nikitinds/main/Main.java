package ru.academit.nikitinds.main;

import ru.academit.nikitinds.controller.Controller;
import ru.academit.nikitinds.controller.DesktopController;
import ru.academit.nikitinds.view.DesktopView;
import ru.academit.nikitinds.view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new DesktopController();

        View view = new DesktopView(controller);
        controller.setView(view);

        view.start();
    }
}