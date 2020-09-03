package ru.academit.nikitinds.lambdas;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }

        if (age <= 0 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть в диапазоне от 1 до 100 включительно");
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) || (Character.getNumericValue(name.charAt(i)) >= 10 && Character.getNumericValue(name.charAt(i)) <= 35)) {
                throw new IllegalArgumentException("Имя должно состоять только букв русского алфавита");
            }

            if (i == 0) {
                if (Character.isLowerCase(name.charAt(i))) {
                    throw new IllegalArgumentException("Имя должно начинаться с заглавной буквы");
                }
            } else {
                if (Character.isUpperCase(name.charAt(i))) {
                    throw new IllegalArgumentException("В имени заглавной должна быть только первая буква");
                }
            }
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}