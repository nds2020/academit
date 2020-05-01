package ru.academit.nikitinds.list_main;

import ru.academit.nikitinds.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Double> numbers = new SinglyLinkedList<>();

        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        numbers.addFirst(10.0);
        System.out.println("Добавили в начало списка элемент " + numbers.getFirst());
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        double element = 20;
        int index = 0;
        numbers.add(index, element);
        System.out.println("Добавили по индексу " + index + " элемент " + element);
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        element = 30;
        index = 1;
        numbers.add(index, element);
        System.out.println("Добавили по индексу " + index + " элемент " + element);
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        element = 40;
        index = numbers.getSize();
        numbers.add(index, element);
        System.out.println("Добавили по индексу " + index + " (в конец списка) элемент " + element);
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        SinglyLinkedList<Double> copyOfNumbers = numbers.copy();
        System.out.println("Скопировали список " + copyOfNumbers);
        System.out.println("Длина скопированного списка равна " + copyOfNumbers.getSize());
        System.out.println();

        numbers.revert();
        System.out.println("Развернули список " + numbers);
        System.out.println();

        index = 3;
        System.out.println("Элемент с индексом " + index + " в списке равен " + numbers.get(index));
        System.out.println("Удалили из списка по индексу " + index + " элемент " + numbers.remove(index));
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        System.out.println("Удалили из списка первый элемент " + numbers.removeFirst());
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
        System.out.println();

        double newElement = 30;
        if (numbers.remove(newElement)) {
            System.out.println("Удалили из списка элемент " + newElement);
            System.out.println("В списке следующие элементы " + numbers);
            System.out.println("Длина списка равна " + numbers.getSize());
            System.out.println();
        } else {
            System.out.println("Пытались удалить из списка элемент " + newElement + ", но его не оказалось в списке");
        }

        index = 0;
        System.out.println("Изменили в списке элемент " + numbers.set(index, newElement) + " на " + newElement);
        System.out.println("В списке следующие элементы " + numbers);
        System.out.println("Длина списка равна " + numbers.getSize());
    }
}