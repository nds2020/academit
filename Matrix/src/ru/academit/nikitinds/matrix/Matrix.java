package ru.academit.nikitinds.matrix;

import ru.academits.nikitinds.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Указанное для создания матрицы количество строк " + rowsCount +
                    " и столбцов " + columnsCount + " должно быть больше 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] arrays) {
        if (arrays.length == 0) {
            throw new IllegalArgumentException("Для создания матрицы из двумерного массива в нем должен быть хотя бы один " +
                    "непустой одномерный массив");
        }

        int columnsCount = 0;

        for (double[] array : arrays) {
            columnsCount = Math.max(columnsCount, array.length);
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Для создания матрицы из двумерного массива в нем должен быть хотя бы один " +
                    "непустой одномерный массив");
        }

        rows = new Vector[arrays.length];

        for (int i = 0; i < arrays.length; i++) {
            rows[i] = new Vector(columnsCount, arrays[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Для создания матрицы из массива векторов в массиве должен быть хотя бы " +
                    "один вектор");
        }

        int columnsCount = 0;

        for (Vector vector : vectors) {
            columnsCount = Math.max(columnsCount, vector.getSize());
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс запрошенного вектора-строки " + index + " вышел за границы " +
                    "индексов строк матрицы");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс задаваемого вектора-строки " + index + " вышел за границы " +
                    "индексов строк матрицы");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество компонент задаваемого вектора-строки " + vector.getSize() +
                    " должно быть равно количеству столбцов " + getColumnsCount() + " в матрице");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс запрошенного вектора-столбца " + index + " вышел за границы " +
                    "индексов столбцов матрицы");
        }

        Vector columnVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            columnVector.setComponent(i, rows[i].getComponent(index));
        }

        return columnVector;
    }

    public void transpose() {
        int rowsCount = getColumnsCount();

        Vector[] newRows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("Для вычисления определителя количество строк " + rows.length +
                    " и столбцов " + getColumnsCount() + " в матрице должно быть одинаковым");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < rows.length; i++) {
            int subMatrixSize = rows.length - 1;

            Matrix subMatrix = new Matrix(subMatrixSize, subMatrixSize);

            for (int j = 0; j < subMatrixSize; j++) {
                int crossedOutColumnsCount = 0;

                for (int k = 0; k <= subMatrixSize; k++) {
                    if (k == i) {
                        crossedOutColumnsCount++;
                        continue;
                    }

                    subMatrix.rows[j].setComponent(k - crossedOutColumnsCount, rows[j + 1].getComponent(k));
                }
            }

            determinant += Math.pow(-1, i) * rows[0].getComponent(i) * subMatrix.getDeterminant();
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Для умножения матрицы на вектор количество столбцов " + getColumnsCount() +
                    " в матрице должно быть равно количеству компонент " + vector.getSize() + " в векторе");
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Прибавить к матрице можно только матрицу такого же размера");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычесть из матрицы можно только матрицу такого же размера");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    @Override
    public String toString() {
        int lastIndex = rows.length - 1;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < lastIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[lastIndex]).append("}");

        return stringBuilder.toString();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Сложить можно только матрицы одного размера");
        }

        Matrix sum = new Matrix(matrix1);

        sum.add(matrix2);
        return sum;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Получить разность можно только между матрицами одного размера");
        }

        Matrix difference = new Matrix(matrix1);

        difference.subtract(matrix2);
        return difference;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов первой матрицы " + matrix1.getColumnsCount() +
                    " должно быть равно количеству строк второй матрицы " + matrix2.getRowsCount());
        }

        Matrix product = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                product.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return product;
    }
}