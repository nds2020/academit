package ru.academit.nikitinds.tree_main;


import ru.academit.nikitinds.tree.MyTree;
import ru.academit.nikitinds.tree.MyTreeNode;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer> tree1 = new MyTree<>(Integer::compareTo);

        tree1.add(new MyTreeNode<>(10));
        tree1.add(new MyTreeNode<>(8));
        tree1.add(new MyTreeNode<>(12));
        tree1.add(new MyTreeNode<>(7));
        tree1.add(new MyTreeNode<>(11));
        tree1.add(new MyTreeNode<>(13));
        tree1.add(new MyTreeNode<>(5));
        tree1.add(new MyTreeNode<>(16));
        tree1.add(new MyTreeNode<>(15));

        System.out.println("Обошли дерево в ширину:");
        System.out.println(tree1.toArrayListByBreadthFirstIteration());

        System.out.println("Обошли дерево в глубину:");
        System.out.println(tree1.toArrayListByDepthFirstIteration());

        System.out.println("Обошли дерево в глубину рекурсивно:");
        System.out.println(tree1.toArrayListByRecurseDepthFirstIteration());
        System.out.println();

        int number = 3;
        if (tree1.contains(number)) {
            System.out.println("Дерево содержит число " + number);
        } else {
            System.out.println("Дерево не содержит число " + number);
        }

        System.out.println();

        System.out.println("Количество элементов в дереве равно: " + tree1.size());
        number = 15;
        tree1.remove(number);
        System.out.println("Удалили число " + number + " - лист дерева");
        System.out.println("Обошли дерево в ширину: " + tree1.toArrayListByBreadthFirstIteration());
        System.out.println("Количество элементов в дереве равно: " + tree1.size());
        System.out.println();

        number = 7;
        tree1.remove(number);
        System.out.println("Удалили число " + number + " - узел с 1 ребенком");
        System.out.println("Вновь обошли дерево в ширину: " + tree1.toArrayListByBreadthFirstIteration());
        System.out.println("Количество элементов в дереве равно: " + tree1.size());
        System.out.println();

        number = 12;
        tree1.remove(number);
        System.out.println("Удалили число " + number + " - узел с 2 детьми");
        System.out.println("Вновь обошли дерево в ширину: " + tree1.toArrayListByBreadthFirstIteration());
        System.out.println("Количество элементов в дереве равно: " + tree1.size());
        System.out.println();

        MyTree<String> tree2 = new MyTree<>(new MyTreeNode<>("abcd"), String::lastIndexOf);

        tree2.add(new MyTreeNode<>("abc"));
        tree2.add(new MyTreeNode<>("abcde"));
        tree2.add(new MyTreeNode<>("ab"));
        tree2.add(new MyTreeNode<>("abcdef"));

        System.out.println("Создали дерево строк и обошли его в ширину " + tree2.toArrayListByBreadthFirstIteration());
    }
}