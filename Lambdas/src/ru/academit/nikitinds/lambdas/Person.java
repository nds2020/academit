package ru.academit.nikitinds.lambdas;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        checkAge(age);
        checkName(name);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        checkAge(age);
        this.age = age;
    }

    private static void checkAge(int age) {
        if (age <= 0 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть в диапазоне от 1 до 100 включительно");
        }
    }

    private static void checkName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }

        if (!Character.isLetter(name.charAt(0)) || Character.isLowerCase(name.charAt(0)) ||
                (Character.getNumericValue(name.charAt(0)) >= 10 && Character.getNumericValue(name.charAt(0)) <= 35)) {
            throw new IllegalArgumentException("Первая буква в имени должна быть заглавной и имя должно состоять только из букв русского алфавита");
        }

        for (int i = 1; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) || Character.isUpperCase(name.charAt(i)) ||
                    (Character.getNumericValue(name.charAt(i)) >= 10 && Character.getNumericValue(name.charAt(i)) <= 35)) {
                throw new IllegalArgumentException("В имени заглавной должна быть только первая буква и имя должно состоять только из букв русского алфавита");
            }
        }
    }
}