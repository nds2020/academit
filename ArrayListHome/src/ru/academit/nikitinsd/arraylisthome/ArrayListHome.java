package ru.academit.nikitinsd.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        }

        System.out.println("Прочитали в список строки из файла " + strings);

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 2, null, 8, 3, 2, 3, 6, null, null, 7, 5, 5, 3, 7, 12, 12));

        System.out.println("Оригинальный список чисел " + numbers);

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == null || numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }

        System.out.println("Оставили только нечетные числа в оригинальном списке " + numbers);

        List<Integer> uniqueNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (number == null || uniqueNumbers.contains(number)) {
                continue;
            }

            uniqueNumbers.add(number);
        }

        System.out.println("Список без повторений " + uniqueNumbers);
    }
}