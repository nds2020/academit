package vector_main;

import ru.academits.nikitinds.Vector;

public class Main {
    public static void main(String[] args) {
        try {
            int size = 7;
            double[] components = {1, 2, 3};
            double[] components2 = {1, 2, 3, 4, 5, 6};

            // проверяем конструкторы
            Vector vector1 = new Vector(size);

            System.out.println("Вектор1 создали конструктором Vector(n) " + vector1);

            Vector vector2 = new Vector(components);

            System.out.println("Вектор2 создали конструктором Vector(double[]) " + vector2);

            Vector vector3 = new Vector(size, components2);

            System.out.println("Вектор3 создали конструктором Vector(n, double[]) " + vector3);

            Vector vector4 = new Vector(vector2);

            System.out.println("Вектор4 - копия Вектора2 " + vector4);
            System.out.println();

            // проверяем нестатические методы
            System.out.println("К Вектору3 добавили Вектор4, результатом будет новый вектор " + vector3.addVector(vector4));
            System.out.println("Из Вектора4 вычли Вектор3, результатом будет новый вектор " + vector4.subtractVector(vector3));
            System.out.println("Вектор3 умножили на скаляр \"2\", результатом будет новый вектор " + vector3.multiplyByScalar(2));
            System.out.println("Развернули Вектор4, результатом будет новый вектор " + vector4.revert());
            System.out.printf("Длина Вектора4 равна %.2f%n%n", vector4.getLength());

            vector2.setComponent(1, vector3.getComponent(5));
            System.out.println("Заменили в Векторе2 компоненту с индексом 1 на компоненту Вектора3 с индексом 5, теперь Вектор2 такой " + vector2);

            System.out.println("Сравниваем Вектор2 и Вектор4: " + (vector2.equals(vector4) ? "Вектора равны" : "Вектора не равны"));

            vector4.setComponent(1, vector2.getComponent(1));
            System.out.println("Заменили в Векторе4 компоненту с индексом 1 на компоненту Вектора2 с индексом 1, теперь Вектор4 такой " + vector4);

            System.out.println("Снова сравниваем Вектор2 и Вектор4: " + (vector2.equals(vector4) ? "Вектора равны" : "Вектора не равны"));
            System.out.println();

            //проверяем статические методы
            Vector vector5 = Vector.getVectorsSum(vector2, vector4);

            System.out.println("Вектор5 " + vector5 + " - это сумма векторов 2 и 4" );

            Vector vector6 = Vector.getVectorsDifference(vector2, vector4);

            System.out.println("Вектор6 " + vector6 + " - это разность векторов 2 и 4 ");

            System.out.printf("Скалярное произведение векторов 5 и 6 равно %.2f%n", Vector.getScalarMultiplication(vector5, vector6));
        } catch (IllegalArgumentException e) {
            System.out.println("Введены некорректные данные - " + e.getLocalizedMessage());
        }
    }
}