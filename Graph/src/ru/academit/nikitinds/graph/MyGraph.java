package ru.academit.nikitinds.graph;

import java.util.*;
import java.util.function.Consumer;

public class MyGraph {
    private final int[][] adjacencyMatrix;

    public MyGraph(int[][] adjacencyMatrix) {
        checkForNull(adjacencyMatrix);

        if (adjacencyMatrix.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть больше 0");
        }

        for (int[] row : adjacencyMatrix) {
            if (row.length != adjacencyMatrix.length) {
                throw new IllegalArgumentException("Аргумент должен быть квадратной матрицей");
            }
        }

        this.adjacencyMatrix = adjacencyMatrix;
    }

    private static void checkForNull(Object arg) {
        if (arg == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }
    }

    public void breadthFirstIteration(Consumer<Integer> consumer) {
        checkForNull(consumer);

        boolean[] visitedVertices = new boolean[adjacencyMatrix.length];

        Deque<Integer> queue = new ArrayDeque<>(adjacencyMatrix.length);

        int vertex;

        for (int i = 0; i < visitedVertices.length; i++) {
            if (!visitedVertices[i]) {
                queue.add(i);

                while (!queue.isEmpty()) {
                    vertex = queue.remove();

                    if (!visitedVertices[vertex]) {
                        consumer.accept(vertex);
                        visitedVertices[vertex] = true;

                        for (int j = 0; j < adjacencyMatrix.length; j++) {
                            if (!visitedVertices[j] && adjacencyMatrix[vertex][j] != 0) {
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }
    }

    public void depthFirstIteration(Consumer<Integer> consumer) {
        checkForNull(consumer);

        boolean[] visitedVertices = new boolean[adjacencyMatrix.length];

        Deque<Integer> stack = new ArrayDeque<>(adjacencyMatrix.length);

        int vertex;

        for (int i = 0; i < visitedVertices.length; i++) {
            if (!visitedVertices[i]) {
                stack.push(i);

                while (!stack.isEmpty()) {
                    vertex = stack.pop();

                    if (!visitedVertices[vertex]) {
                        consumer.accept(vertex);
                        visitedVertices[vertex] = true;

                        for (int j = adjacencyMatrix.length - 1; j >= 0; j--) {
                            if (!visitedVertices[j] && adjacencyMatrix[vertex][j] != 0) {
                                stack.push(j);
                            }
                        }
                    }
                }
            }
        }
    }

    public void recurseDepthFirstIteration(Consumer<Integer> consumer) {
        checkForNull(consumer);

        boolean[] visitedVertices = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < visitedVertices.length; i++) {
            if (!visitedVertices[i]) {
                visitVertex(i, visitedVertices, consumer);
            }
        }
    }

    private void visitVertex(int vertex, boolean[] visitedVertices, Consumer<Integer> consumer) {
        visitedVertices[vertex] = true;
        consumer.accept(vertex);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visitedVertices[i] && adjacencyMatrix[vertex][i] != 0) {
                visitVertex(i, visitedVertices, consumer);
            }
        }
    }
}