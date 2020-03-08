package ru.academits.nikitinds.vector_main;

import ru.academits.nikitinds.vector.Vector;

public class Main {
    public static void main(String[] args) {
        try {
            int size = 7;
            double[] components1 = {1, 2, 3};
            double[] components2 = {4, 5, 6, 7, 8};

            // проверяем конструкторы
            Vector vector1 = new Vector(size);

            System.out.println("Вектор1 " + vector1 + " создали конструктором Vector(n)");

            Vector vector2 = new Vector(components1);

            System.out.println("Вектор2 " + vector2 + " создали конструктором Vector(double[])");

            Vector vector3 = new Vector(size, components2);

            System.out.println("Вектор3 " + vector3 + " создали конструктором Vector(n, double[])");

            Vector vector4 = new Vector(vector3);

            System.out.println("Вектор4 " + vector4 + " - копия Вектора3");

            Vector vector5 = new Vector(vector2);

            System.out.println("Вектор5 " + vector5 + " - копия Вектора2");
            System.out.println();

            // проверяем нестатические методы
            vector1.add(vector2);
            System.out.println("К вектору1 добавили вектор2, вектор1 теперь такой " + vector1);

            vector2.add(vector3);
            System.out.println("К вектору2 добавили вектор3, вектор2 теперь такой " + vector2 + ", а вектор1 остался прежним " + vector1);
            System.out.println();

            vector4.subtract(vector5);
            System.out.println("Из вектора4 вычли вектор5, вектор4 теперь такой " + vector4);

            vector5.subtract(vector3);
            System.out.println("Из вектора5 вычли вектор3, вектор5 теперь такой " + vector5 + ", а вектор4 остался прежним " + vector4);

            vector5.multiplyByScalar(2);
            System.out.println("Вектор5 умножили на скаляр \"2\", вектор5 теперь такой " + vector5 + ", а вектор4 не изменился " + vector4);

            vector5.revert();
            System.out.println("Развернули вектор5, он теперь такой " + vector5 + ", а вектор4 прежний " + vector4);
            System.out.println();

            vector5.setComponent(vector5.getSize() - 1, vector4.getComponent(0));
            System.out.println("Заменили последнюю компоненту вектора5 на первую компоненту вектора4, вектор5 теперь такой " + vector5 + ", а вектор4 не изменился " + vector4);
            System.out.println("Сейчас " + (vector5.equals(vector4) ? "вектор5 равен вектору4" : "вектор5 не равен вектору4"));

            vector5.setComponent(vector5.getSize() - 1, vector4.getComponent(vector5.getSize() - 1));
            vector5.multiplyByScalar(0.5);
            System.out.println("Заменили последнюю компоненту вектора5 на последнюю компоненту вектора4, затем умножили вектор5 на скаляр 0,5, вектор5 теперь такой " + vector5);
            System.out.println("И теперь " + (vector5.equals(vector4) ? "вектор5 равен вектору4" : "вектор5 не равен вектору4"));
            System.out.println();

            System.out.printf("Длина вектора5 равна %.2f, длина вектора4 равна %.2f%n%n", vector5.getModule(), vector4.getModule());

            //проверяем статические методы
            Vector vector6 = Vector.getSum(vector4, vector5);

            System.out.println("Вектор6 " + vector6 + " - это сумма векторов 4 и 5");
            System.out.println("при этом вектор4 " + vector4 + " и вектор5 " + vector5 + " не изменились");
            System.out.println();

            Vector vector7 = new Vector(components1);

            System.out.println("Создали вектор7 " + vector7);
            System.out.println();

            Vector vector8 = Vector.getSum(vector7, vector6);

            System.out.println("Вектор8 " + vector8 + " - это сумма векторов 7 и 6");
            System.out.println("при этом вектор7 " + vector7 + " и вектор6 " + vector6 + " не изменились");
            System.out.println();

            Vector vector9 = Vector.getDifference(vector8, vector6);

            System.out.println("Вектор9 " + vector9 + " - это разность векторов 8 и 6");
            System.out.println("при этом вектор8 " + vector8 + " и вектор6 " + vector6 + " не изменились");
            System.out.println();

            Vector vector10 = Vector.getDifference(vector7, vector6);

            System.out.println("Вектор10 " + vector10 + " - это разность векторов 7 и 6");
            System.out.println("при этом вектор7 " + vector7 + " и вектор6 " + vector6 + " не изменились");
            System.out.println();

            System.out.println(Vector.getScalarProduct(vector9, vector10) + " - это скаларное произведение векторов 9 и 10");
            System.out.println(Vector.getScalarProduct(vector7, vector8) + " - это скаларное произведение векторов 7 и 8");
        } catch (IllegalArgumentException e) {
            System.out.println("Введены некорректные данные - " + e.getLocalizedMessage());
        }
    }
}