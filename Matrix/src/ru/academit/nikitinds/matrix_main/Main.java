package ru.academit.nikitinds.matrix_main;

import ru.academit.nikitinds.matrix.Matrix;
import ru.academits.nikitinds.vector.Vector;

public class Main {
    public static void main(String[] args) {
        int a = 2;
        int b = 4;
        double[][] arrays = {{1, 2, 3}, {4, 5, 2}, {4, 3, 1}};
        Vector v1 = new Vector(new double[]{-1, 2, 3, 4});
        Vector v2 = new Vector(new double[]{3, 2, 5, 4});
        Vector v3 = new Vector(new double[]{1, 5, 6, 7});
        Vector v4 = new Vector(new double[]{2, 1, 5, 4});

        Matrix m1 = new Matrix(a, b);
        System.out.println("Создали матрицу1 " + m1 + " размера " + a + " на " + b);
        System.out.println("У матрицы1 строк: " + m1.getRowsCount() + ", столбцов: " + m1.getColumnsCount());
        System.out.println();

        Matrix m2 = new Matrix(arrays);
        System.out.println("Из двумерного массива создали матрицу2 " + m2);

        Vector rowVector = new Vector(m2.getRow(1));
        System.out.println("Вектор " + rowVector + " это вторая строка матрицы2");

        Vector columnVector = new Vector(m2.getColumn(2));
        System.out.println("Вектор " + columnVector + " это третий столбец матрицы2");

        m2.setRow(0, columnVector);
        System.out.println("В матрице2 заменили первую строку на вектор " + columnVector + ", матрица2 теперь такая " + m2);

        m2.transpose();
        System.out.println("Транспонировали матрицу2, теперь она такая " + m2);

        Vector vector = m2.multiplyByVector(rowVector);
        System.out.println("Умножили матрицу2 на вектор " + rowVector + ", в результате получили новый вектор " + vector);
        System.out.println();

        Matrix m3 = new Matrix(new Vector[]{v1, v2, v3, v4});
        System.out.println("Из массива векторов создали матрицу3 " + m3);

        m3.multiplyByScalar(a);
        System.out.println("Умножили матрицу3 на скаляр " + a + ", теперь матрица3 такая " + m3);

        m3.add(m3);
        System.out.println("Прибавили к матрице3 ее же, теперь она такая " + m3);
        System.out.println();

        Matrix m4 = new Matrix(m3);
        System.out.println("Копированием матрицы3 создали матрицу4 " + m4);

        m4.subtract(m3);
        System.out.println("Вычли из матрицы4 матрицу3, теперь матрица4 такая " + m4);
        System.out.println();

        Matrix m5 = Matrix.getSum(m3, m3);
        System.out.println("Матрица5 " + m5 + " это сумма");
        System.out.println("матрицы3 " + m3 + " самой с собой");
        System.out.println();

        Matrix m6 = Matrix.getDifference(m4, m3);
        System.out.println("Матрица6 " + m6 + " это разница");
        System.out.println("матрицы4 " + m4);
        System.out.println("и матрицы3 " + m3);
        System.out.println();

        Matrix m7 = Matrix.getProduct(m5, m6);
        System.out.println("Матрица7 " + m7 + " это произведение");
        System.out.println("матрицы5 " + m5);
        System.out.println("и матрицы6 " + m6);

        System.out.println("Определитель матрицы5 равен " + m5.getDeterminant() + ", матрицы6 " + m6.getDeterminant());
    }
}