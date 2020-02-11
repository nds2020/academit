package ru.academit.nikitinds.range_main;

import ru.academit.nikitinds.range.Range;

public class Main {
    public static void main(String[] args) {
        Range firstRange = new Range(1, 4);

        Range secondRange = new Range(2, 3);

        Range intersection = firstRange.getIntersection(secondRange);

        if (intersection == null) {
            System.out.println("Указанные интервалы не пересекаются");
        } else {
            System.out.println("Интервал [" + intersection.getFrom() + ", " + intersection.getTo() + "]" + " является пересечением указанных интервалов");
        }

        System.out.println();

        Range[] union = firstRange.getUnion(secondRange);

        if (union[1] != null) {
            System.out.println("Интервал [" + union[0].getFrom() + ", " + union[0].getTo() + "] и интервал [" + union[1].getFrom() + ", " + union[1].getTo() + "] являются объединением указанных интервалов");
        } else {
            System.out.println("Интервал [" + union[0].getFrom() + ", " + union[0].getTo() + "]" + " является объединением указанных интервалов");
        }

        System.out.println();

        Range[] complement = firstRange.getComplement(secondRange);

        if (complement == null) {
            System.out.println("Разность указанных интервалов равна нулю");
        } else {
            if (complement[1] != null) {
                System.out.println("Интервал [" + complement[0].getFrom() + ", " + complement[0].getTo() + "] и интервал [" + complement[1].getFrom() + ", " + complement[1].getTo() + "] являются разностью указанных интервалов");
            } else {
                System.out.println("Интервал [" + complement[0].getFrom() + ", " + complement[0].getTo() + "]" + " является разностью указанных интервалов");
            }
        }
    }
}