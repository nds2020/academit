package ru.academit.nikitinds.range_main;

import ru.academit.nikitinds.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(5, 7);

        Range range2 = new Range(1, 4);

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Указанные интервалы не пересекаются");
        } else {
            System.out.println("Интервал " + intersection.toString() + " - пересечение указанных интервалов");
        }

        System.out.println();

        Range[] union = range1.getUnion(range2);

        System.out.println(("Интервал(ы) " + Arrays.toString(union) + " - объединение указанных интервалов").replace("[", "").replace("]", ""));
        System.out.println();

        Range[] complement = range1.getComplement(range2);

        if (complement.length == 0) {
            System.out.println("Разность указанных интервалов равна нулю");
        } else {
            System.out.println(("Интервал(ы) " + Arrays.toString(complement) + " - разность указанных интервалов").replace("[", "").replace("]", ""));
        }
    }
}