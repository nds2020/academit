package ru.academit.nikitinds.graph;


import java.util.*;

public class MyGraph<T> {
    private final T[][] edges;

    public MyGraph(T[][] edges) {
        if (edges == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        if (edges.length != 0 && edges.length != edges[edges.length - 1].length) {
            throw new IllegalArgumentException("Аргумент должен быть квадратной матрицей");
        }

        this.edges = edges;
    }

    public int[] toArrayByBreadthFirstIteration() {
        int[] array = new int[edges.length];
        boolean[] visitedVertices = new boolean[edges.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < visitedVertices.length; i++) {
            if (!visitedVertices[i]) {
                queue.add(i);
                visitedVertices[i] = true;
            }

            for (int j = 0; j < edges.length; j++) {
                if (edges[i][j] != null && !visitedVertices[j]) {
                    queue.add(j);
                    visitedVertices[j] = true;
                }
            }

            array[i] = queue.remove();
        }

        return array;
    }

    public int[] toArrayByDepthFirstIteration() {
        int[] array = new int[edges.length];
        boolean[] visitedVertices = new boolean[edges.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < visitedVertices.length; i++) {
            if (!visitedVertices[i]) {
                stack.push(i);
                visitedVertices[i] = true;
            }

            array[i] = stack.pop();

            for (int j = edges.length - 1; j >= 0; j--) {
                if (edges[i][j] != null && !visitedVertices[j]) {
                    stack.push(j);
                    visitedVertices[j] = true;
                }
            }
        }

        return array;
    }
}