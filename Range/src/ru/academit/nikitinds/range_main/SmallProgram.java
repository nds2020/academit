package ru.academit.nikitinds.range_main;

import ru.academit.nikitinds.range.Range;

public class SmallProgram {
    public static void main(String[] args) {
        Range range = new Range(2.2, 10.1);

        final double number = 2.5;
        double from = range.getFrom();
        double to = range.getTo();

        if (range.isInside(number)) {
            System.out.printf("Число %.1f принадлежит диапазону чисел от %.1f до %.1f%n", number, from, to);
        } else {
            System.out.printf("Число %.1f не принадлежит диапазону чисел от %.1f до %.1f%n", number, from, to);
        }

        System.out.printf("Длина диапазона чисел от %.1f до %.1f равна %.1f%n", from, to, range.getLength());

        range.setFrom(5);
        range.setTo(13.2);
        from = range.getFrom();
        to = range.getTo();

        if (range.isInside(number)) {
            System.out.printf("Число %.1f принадлежит диапазону чисел от %.1f до %.1f%n", number, from, to);
        } else {
            System.out.printf("Число %.1f не принадлежит диапазону чисел от %.1f до %.1f%n", number, from, to);
        }

        System.out.printf("Длина диапазона чисел от %.1f до %.1f равна %.1f%n", from, to, range.getLength());
    }
}
