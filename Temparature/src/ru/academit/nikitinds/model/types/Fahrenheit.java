package ru.academit.nikitinds.model.types;

public class Fahrenheit implements Type {
    private static final String name = "градусы Фаренгейта";
    private double value;

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return name;
    }
}