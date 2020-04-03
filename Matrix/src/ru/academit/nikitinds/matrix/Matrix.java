package ru.academit.nikitinds.matrix;

import ru.academits.nikitinds.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.rows.length;

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] arrays) {
        int rowsCount = arrays.length;
        int columnsCount = 0;

        for (double[] array : arrays) {
            columnsCount = Math.max(columnsCount, array.length);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount, arrays[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int rowsCount = vectors.length;
        int columnsCount = 0;

        for (Vector vector : vectors) {
            columnsCount = Math.max(columnsCount, vector.getSize());
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
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

    public Vector getRow(int rowIndex) {
        int rowsIndicesCount = rows.length - 1;

        if (rowIndex < 0 || rowIndex > rowsIndicesCount) {
            throw new IndexOutOfBoundsException("Указанный индекс " + rowIndex + " получаемого вектора-строки вышел " +
                    "за границы индексов (0, " + rowsIndicesCount + ") строк матрицы");
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector vector) {
        int rowsIndicesCount = rows.length - 1;

        if (rowIndex < 0 || rowIndex > rowsIndicesCount) {
            throw new IndexOutOfBoundsException("Указанный индекс " + rowIndex + " задаваемого вектора-строки вышел " +
                    "за границы индексов (0, " + rowsIndicesCount + ") строк матрицы");
        }

        rows[rowIndex] = new Vector(vector);
    }

    public Vector getColumn(int columnIndex) {
        int columnsIndicesCount = rows[0].getSize() - 1;

        if (columnIndex < 0 || columnIndex > columnsIndicesCount) {
            throw new IllegalArgumentException("Указанный индекс " + columnIndex + " получаемого вектора-столбца вышел " +
                    "за границы индексов (0, " + columnsIndicesCount + ") столбцов матрицы");
        }

        int columnVectorComponentsCount = rows.length;

        Vector columnVector = new Vector(columnVectorComponentsCount);

        for (int i = 0; i < columnVectorComponentsCount; i++) {
            columnVector.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return columnVector;
    }

    public void transpose() {
        int rowsCount = rows[0].getSize();

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
        int matrixRowsCount = getRowsCount();
        int matrixColumnsCount = getColumnsCount();

        if (matrixRowsCount != matrixColumnsCount) {
            throw new IllegalArgumentException("Количество строк " + matrixRowsCount + " и столбцов " + matrixColumnsCount + " матрицы должны быть равны");
        }

        if (matrixRowsCount == 1) {
            return rows[0].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrixRowsCount; i++) {
            int subMatrixSize = matrixRowsCount - 1;

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

    public void multiplyByVector(Vector vector) {
        int vectorComponentsCount = vector.getSize();

        if (vectorComponentsCount == 1) {
            multiplyByScalar(vector.getComponent(0));
            return;
        }

        int matrixRowsCount = getRowsCount();
        int matrixColumnsCount = getColumnsCount();

        if (matrixColumnsCount == 1) {
            if (matrixRowsCount != vectorComponentsCount) {
                throw new IllegalArgumentException("Для умножения на вектор матрицы, состоящей из одного столбца, " +
                        "количество строк " + matrixRowsCount + " в матрице должно быть равно количеству компонент " +
                        vectorComponentsCount + "в векторе");
            }

            for (int i = 0; i < matrixRowsCount; i++) {
                double temp = rows[i].getComponent(0);

                rows[i] = new Vector(vector);

                rows[i].multiplyByScalar(temp);
            }

            return;
        }

        if (matrixColumnsCount != vectorComponentsCount) {
            throw new IllegalArgumentException("Для умножения на вектор матрицы, состоящей из более чем одного столбца, " +
                    "количество столбцов " + matrixColumnsCount + " в матрице должно быть равно количеству компонент " +
                    vectorComponentsCount + " в векторе");
        }

        for (int i = 0; i < matrixRowsCount; i++) {
            rows[i] = new Vector(new double[]{Vector.getScalarProduct(rows[i], vector)});
        }
    }

    public void add(Matrix matrix) {
        int thisMatrixRowsCount = rows.length;
        int thisMatrixColumnsCount = rows[0].getSize();
        int addedMatrixRowsCount = matrix.rows.length;
        int addedMatrixColumnsCount = matrix.rows[0].getSize();

        if (thisMatrixRowsCount != addedMatrixRowsCount || thisMatrixColumnsCount != addedMatrixColumnsCount) {
            throw new IllegalArgumentException("Для сложения матриц количество строк " + thisMatrixRowsCount + " и столбцов "
                    + thisMatrixColumnsCount + " первой матрицы должны быть соответственно равны количеству строк "
                    + addedMatrixRowsCount + " и столбцов " + addedMatrixColumnsCount + " второй матрицы ");
        }

        for (int i = 0; i < thisMatrixRowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int thisMatrixRowsCount = rows.length;
        int thisMatrixColumnsCount = rows[0].getSize();
        int subtractedMatrixRowsCount = matrix.rows.length;
        int subtractedMatrixColumnsCount = matrix.rows[0].getSize();

        if (thisMatrixRowsCount != subtractedMatrixRowsCount || thisMatrixColumnsCount != subtractedMatrixColumnsCount) {
            throw new IllegalArgumentException("Для вычитания матриц количество строк " + thisMatrixRowsCount + " и столбцов "
                    + thisMatrixColumnsCount + " первой матрицы должны быть соответственно равны количеству строк "
                    + subtractedMatrixRowsCount + " и столбцов " + subtractedMatrixColumnsCount + " второй матрицы ");
        }

        for (int i = 0; i < thisMatrixRowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    @Override
    public String toString() {
        int lastIndex = rows.length - 1;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < lastIndex; i++) {
            stringBuilder.append(rows[i].toString()).append(", ");
        }

        stringBuilder.append(rows[lastIndex].toString()).append("}");

        return stringBuilder.toString();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix sum = new Matrix(matrix1);

        sum.add(matrix2);
        return sum;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix difference = new Matrix(matrix1);

        difference.subtract(matrix2);
        return difference;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int firstMatrixColumnsCount = matrix1.getColumnsCount();
        int secondMatrixRowsCount = matrix2.getRowsCount();

        if (firstMatrixColumnsCount != secondMatrixRowsCount) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов " + firstMatrixColumnsCount
                    + " первой матрицы должно быть равно количеству строк " + secondMatrixRowsCount + " второй матрицы");
        }

        int firstMatrixRowsCount = matrix1.getRowsCount();
        int secondMatrixColumnsCount = matrix2.getColumnsCount();

        Matrix product = new Matrix(firstMatrixRowsCount, secondMatrixColumnsCount);

        for (int i = 0; i < firstMatrixRowsCount; i++) {
            for (int j = 0; j < secondMatrixColumnsCount; j++) {
                product.rows[i].setComponent(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return product;
    }
}