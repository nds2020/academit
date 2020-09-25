package ru.academit.nikitinds.graph_main;

import ru.academit.nikitinds.graph.MyGraph;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {{0, 1, 0, 1}, {1, 0, 0, 1}, {0, 0, 0, 0}, {1, 1, 0, 0}};

        MyGraph graph1 = new MyGraph(matrix1);

        System.out.println("Обошли в ширину graph1:");
        graph1.breadthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Обошли в глубину graph1:");
        graph1.depthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Рекурсивно обошли в глубину graph1:");
        graph1.recurseDepthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();

        MyGraph graph2 = new MyGraph(new int[4][4]);

        System.out.println("Обошли в ширину graph2");
        graph2.breadthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Обошли в глубину graph2:");
        graph2.depthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Рекурсивно обошли в глубину graph2:");
        graph2.recurseDepthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();

        int[][] matrix3 = {{0, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1, 0,}, {0, 1, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 1, 0}};

        MyGraph graph3 = new MyGraph(matrix3);

        System.out.println("Обошли в ширину graph3");
        graph3.breadthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Обошли в глубину graph3:");
        graph3.depthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Рекурсивно обошли в глубину graph3:");
        graph3.recurseDepthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();

        int[][] matrix4 = {{0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0,}, {0, 0, 0, 0, 0, 0}};

        MyGraph graph4 = new MyGraph(matrix4);

        System.out.println("Обошли в ширину graph4");
        graph4.breadthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Обошли в глубину graph4:");
        graph4.depthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
        System.out.println("Рекурсивно обошли в глубину graph4:");
        graph4.recurseDepthFirstIteration(vertex -> System.out.printf("%d ", vertex));
        System.out.println();
    }
}