package ru.academit.nikitinds.array_list_main;

import ru.academit.nikitinds.array_list.MyArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> integers = new MyArrayList<>(Arrays.asList(1, 2, 3, 4));
        MyArrayList<String> strings = new MyArrayList<>();
        MyArrayList<Number> numbers = new MyArrayList<>(5);

        System.out.println("Список integers: " + integers);
        System.out.println("Список strings: " + strings);
        System.out.println("Список numbers: " + numbers);
        System.out.println();

        integers.ensureCapacity(7);
        System.out.println("Увеличсилил вместимость integers, она теперь равна " + integers.capacity());
        integers.trimToSize();
        System.out.println("Уменьшили вместимость integers, она теперь равна " + integers.capacity());
        System.out.println();

        integers.set(0, integers.get(integers.size() - 1));
        System.out.println("Заменили в integers элемент по индексу 0: " + integers);
        System.out.println();

        strings.add("Hello");
        strings.add(strings.size(), "World!");
        System.out.println("Добавили элементы в strings: " + strings);
        System.out.println();

        numbers.addAll(integers);
        System.out.println("Добавили в numbers все элементы integers, numbers теперь содержит: " + numbers);
        System.out.println();

        numbers.remove(Integer.valueOf(4));
        System.out.println("Удалили из numbers элемент 4: " + numbers);
        numbers.remove(numbers.size() - 1);
        System.out.println("Удалили из numbers элемент по индексу: " + numbers);
        numbers.removeAll(integers);
        System.out.println("Удалили из numbers все элементы, которые есть в integers, numbers теперь содержит: " + numbers);
        System.out.println("Размер numbers равен " + numbers.size() + ", вместимость numbers равна " + numbers.capacity());
        System.out.println();

        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Добавили в numbers элементы, numbers теперь такой: " + numbers);
        numbers.retainAll(integers);
        System.out.println("Оставили в numbers только те элементы, которые есть в integers, numbers теперь такой: " + numbers);
        System.out.println("Размер numbers равен " + numbers.size());
        System.out.println();

        if (numbers.containsAll(integers)) {
            System.out.println("numbers содержит все элементы integers");
        } else {
            System.out.println("numbers не содержит все элементы integers");
        }

        String string = "Hello";

        if (strings.contains(string)) {
            System.out.println("strings содержит " + string);
        } else {
            System.out.println("numbers не содержит " + string);
        }

        System.out.println();

        int number = 4;
        System.out.println("Число " + number + " впервые встречается в integers по индексу " + integers.indexOf(number));
        System.out.println("Число " + number + " в последний раз встречается в integers по индексу " + integers.lastIndexOf(number));
        System.out.println();

        if (!integers.isEmpty()) {
            integers.clear();
            System.out.println("Очистили integers: " + integers);
            System.out.println("Размер integers равен " + integers.size() + ", вместимость integers равна " + integers.capacity());
        }
    }
}
