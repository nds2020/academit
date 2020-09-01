package ru.academit.nikitinds.array_list_home;

import java.io.*;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            System.out.println("Прочитали в список строки из файла: " + strings);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
        }

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 8, 3, 2, 3, 6, 7, 5, 5, 3, 7, 12, 12));

        System.out.println("Оригинальный список чисел: " + numbers);

        // можно так удалить
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }

        /* а можно и так
        numbers.removeIf(integer -> integer % 2 == 0);*/

        System.out.println("Оставили только нечетные числа в оригинальном списке: " + numbers);
        System.out.println();

        List<Integer> uniqueNumbers = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        System.out.println("На основе списка нечетных чисел сделали другой список без повторений элементов: " + uniqueNumbers);
    }
}