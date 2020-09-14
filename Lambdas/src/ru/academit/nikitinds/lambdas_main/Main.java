package ru.academit.nikitinds.lambdas_main;

import ru.academit.nikitinds.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Ольга", 31),
                new Person("Ольга", 20),
                new Person("Сергей", 28),
                new Person("Дмитрий", 20),
                new Person("Ирина", 30),
                new Person("Максим", 50),
                new Person("Максим", 5),
                new Person("Елена", 17),
                new Person("Игорь", 16),
                new Person("Артём", 15)
        ));

        // задание А) получить список уникальных имен
        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        /* задание Б) вывести список уникальных имен в формате:
        Имена: Иван, Сергей, Петр.*/
        System.out.println(uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."))
        );

        // В) получить список людей младше 18, посчитать для них средний возраст
        // можно так
        double averageAge1 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);
        System.out.printf("Средний возраст людей младше 18 лет равен %.2f%n", averageAge1);

        //либо так
        double averageAge2 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.averagingDouble(Person::getAge));
        System.out.printf("Средний возраст людей младше 18 лет равен %.2f%n", averageAge2);

        // Г) при помощи группировки получить Map, в котором ключи имена, а значения средний возраст
        Map<String, Double> averageAgeByNames = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(averageAgeByNames);

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        String names = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей от 20 до 45 лет в порядке убывания возраста: ", "."));
        System.out.println(names);
    }
}