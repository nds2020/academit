package ru.academit.nikitinds.graph_main;

import ru.academit.nikitinds.graph.MyGraph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix1 = new Integer[5][5];

        matrix1[0][0] = null;
        matrix1[0][1] = 1;
        matrix1[0][2] = null;
        matrix1[0][3] = 1;
        matrix1[0][4] = null;
        matrix1[1][0] = 1;
        matrix1[1][1] = null;
        matrix1[1][2] = 1;
        matrix1[1][3] = 1;
        matrix1[1][4] = null;
        matrix1[2][0] = null;
        matrix1[2][1] = 1;
        matrix1[2][2] = null;
        matrix1[2][3] = null;
        matrix1[2][4] = 1;
        matrix1[3][0] = 1;
        matrix1[3][1] = 1;
        matrix1[3][2] = null;
        matrix1[3][3] = null;
        matrix1[3][4] = 1;
        matrix1[4][0] = null;
        matrix1[4][1] = null;
        matrix1[4][2] = 1;
        matrix1[4][3] = 1;
        matrix1[4][4] = null;

        MyGraph<Integer> graph1 = new MyGraph<>(matrix1);

        System.out.println("Обошли в ширину graph1 " + Arrays.toString(graph1.toArrayByBreadthFirstIteration()));
        System.out.println("Обошли в глубину graph1 " + Arrays.toString(graph1.toArrayByDepthFirstIteration()));

        MyGraph<Integer> graph2 = new MyGraph<>(new Integer[0][0]);

        System.out.println("Обошли в ширину graph2 " + Arrays.toString(graph2.toArrayByBreadthFirstIteration()));
        System.out.println("Обошли в глубину graph2 " + Arrays.toString(graph2.toArrayByDepthFirstIteration()));

        Integer[][] matrix3 = new Integer[7][7];

        matrix3[0][0] = null;
        matrix3[0][1] = 1;
        matrix3[0][2] = null;
        matrix3[0][3] = null;
        matrix3[0][4] = null;
        matrix3[0][5] = null;
        matrix3[0][6] = null;
        matrix3[1][0] = 1;
        matrix3[1][1] = null;
        matrix3[1][2] = 1;
        matrix3[1][3] = 1;
        matrix3[1][4] = 1;
        matrix3[1][5] = 1;
        matrix3[1][6] = null;
        matrix3[2][0] = null;
        matrix3[2][1] = 1;
        matrix3[2][2] = null;
        matrix3[2][3] = null;
        matrix3[2][4] = null;
        matrix3[2][5] = null;
        matrix3[2][6] = 1;
        matrix3[3][0] = null;
        matrix3[3][1] = 1;
        matrix3[3][2] = null;
        matrix3[3][3] = null;
        matrix3[3][4] = null;
        matrix3[3][5] = null;
        matrix3[3][6] = null;
        matrix3[4][0] = null;
        matrix3[4][1] = 1;
        matrix3[4][2] = null;
        matrix3[4][3] = null;
        matrix3[4][4] = null;
        matrix3[4][5] = 1;
        matrix3[4][6] = null;
        matrix3[5][0] = null;
        matrix3[5][1] = 1;
        matrix3[5][2] = null;
        matrix3[5][3] = null;
        matrix3[5][4] = 1;
        matrix3[5][5] = null;
        matrix3[5][6] = 1;
        matrix3[6][0] = null;
        matrix3[6][1] = null;
        matrix3[6][2] = 1;
        matrix3[6][3] = null;
        matrix3[6][4] = null;
        matrix3[6][5] = 1;
        matrix3[6][6] = null;

        MyGraph<Integer> graph3 = new MyGraph<>(matrix3);

        System.out.println("Обошли в ширину graph3 " + Arrays.toString(graph3.toArrayByBreadthFirstIteration()));
        System.out.println("Обошли в глубину graph3 " + Arrays.toString(graph3.toArrayByDepthFirstIteration()));

        Integer[][] matrix4 = new Integer[6][6];

        matrix4[0][0] = null;
        matrix4[0][1] = null;
        matrix4[0][2] = null;
        matrix4[0][3] = null;
        matrix4[0][4] = null;
        matrix4[0][5] = null;
        matrix4[1][0] = null;
        matrix4[1][1] = null;
        matrix4[1][2] = 1;
        matrix4[1][3] = null;
        matrix4[1][4] = null;
        matrix4[1][5] = null;
        matrix4[2][0] = null;
        matrix4[2][1] = 1;
        matrix4[2][2] = null;
        matrix4[2][3] = 1;
        matrix4[2][4] = 1;
        matrix4[2][5] = null;
        matrix4[3][0] = null;
        matrix4[3][1] = null;
        matrix4[3][2] = 1;
        matrix4[3][3] = null;
        matrix4[3][4] = 1;
        matrix4[3][5] = null;
        matrix4[4][0] = null;
        matrix4[4][1] = null;
        matrix4[4][2] = 1;
        matrix4[4][3] = 1;
        matrix4[4][4] = null;
        matrix4[4][5] = null;
        matrix4[5][0] = null;
        matrix4[5][1] = null;
        matrix4[5][2] = null;
        matrix4[5][3] = null;
        matrix4[5][4] = null;
        matrix4[5][5] = null;

        MyGraph<Integer> graph4 = new MyGraph<>(matrix4);

        System.out.println("Обошли в ширину graph4 " + Arrays.toString(graph4.toArrayByBreadthFirstIteration()));
        System.out.println("Обошли в глубину graph4 " + Arrays.toString(graph4.toArrayByDepthFirstIteration()));
    }
}