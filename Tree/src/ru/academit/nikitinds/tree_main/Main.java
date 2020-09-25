package ru.academit.nikitinds.tree_main;

import ru.academit.nikitinds.tree.MyTree;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> integersTree = new MyTree<>();

        integersTree.add(9);
        integersTree.add(8);
        integersTree.add(12);
        integersTree.add(7);
        integersTree.add(11);
        integersTree.add(null);
        integersTree.add(5);
        integersTree.add(16);
        integersTree.add(15);

        System.out.println("Обошли дерево в ширину:");
        integersTree.breadthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();

        System.out.println("Обошли дерево в глубину:");
        integersTree.depthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();

        System.out.println("Обошли дерево в глубину рекурсивно:");
        integersTree.recurseDepthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();
        System.out.println();

        int number = 3;
        if (integersTree.contains(number)) {
            System.out.println("Дерево содержит число " + number);
        } else {
            System.out.println("Дерево не содержит число " + number);
        }

        System.out.println();

        System.out.println("Количество элементов в дереве равно: " + integersTree.size());
        number = 15;
        integersTree.remove(number);
        System.out.println("Удалили число " + number + " - лист дерева");
        System.out.println("Обошли дерево в ширину:");
        integersTree.breadthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();
        System.out.println("Количество элементов в дереве равно: " + integersTree.size());
        System.out.println();

        number = 7;
        integersTree.remove(number);
        System.out.println("Удалили число " + number + " - узел с 1 ребенком");
        System.out.println("Вновь обошли дерево в ширину:");
        integersTree.breadthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();
        System.out.println("Количество элементов в дереве равно: " + integersTree.size());
        System.out.println();

        number = 12;
        integersTree.remove(number);
        System.out.println("Удалили число " + number + " - узел с 2 детьми");
        System.out.println("Вновь обошли дерево в ширину:");
        integersTree.breadthFirstIteration(integer -> System.out.printf("%d ", integer));
        System.out.println();
        System.out.println("Количество элементов в дереве равно: " + integersTree.size());
        System.out.println();

        MyTree<String> stringsTree = new MyTree<>(String::compareToIgnoreCase);

        stringsTree.add("abcd");
        stringsTree.add(null);
        stringsTree.add("abCde");
        stringsTree.add("ab");
        stringsTree.add("abcDef");
        stringsTree.add(null);

        System.out.println("Создали дерево строк и обошли его в ширину:");
        stringsTree.breadthFirstIteration(string -> System.out.printf("%s ", string));
        System.out.println();
        System.out.println("Обошли дерево строк в глубину:");
        stringsTree.depthFirstIteration(string -> System.out.printf("%s ", string));
        System.out.println();
    }
}