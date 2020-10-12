package ru.academit.nikitinds.view;

public enum TemperatureTypes {
    CELSIUS("градусы Цельсия"),
    KELVIN("градусы Кельвина"),
    FAHRENHEIT("градусы Фаренгейта");

    private final String name;

    TemperatureTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getNames() {
        String[] names = new String[values().length];

        for (TemperatureTypes en : values()) {
            names[en.ordinal()] = en.getName();
        }

        return names;
    }
}