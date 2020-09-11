package ru.academit.nikitinds.tree;

import java.util.*;
import java.util.function.Consumer;

public class MyTree<T extends Comparable<T>> {
    private final Comparator<T> comparator;
    private MyTreeNode<T> root;
    private int nodesCount;

    public MyTree() {
        comparator = Comparator.nullsFirst(Comparator.naturalOrder());
    }

    public MyTree(Comparator<T> comparator) {
        this.comparator = Comparator.nullsFirst(comparator);
    }

    public MyTree(T data, Comparator<T> comparator) {
        this.root = new MyTreeNode<>(data);
        this.comparator = Comparator.nullsFirst(comparator);
        nodesCount++;
    }

    public int size() {
        return nodesCount;
    }

    public boolean add(T data) {
        MyTreeNode<T> node = new MyTreeNode<>(data);

        if (root == null) {
            root = node;
            nodesCount++;
            return true;
        }

        MyTreeNode<T> parent = root;

        while (true) {
            if (comparator.compare(node.getData(), parent.getData()) < 0) {
                if (parent.getLeftChild() != null) {
                    parent = parent.getLeftChild();
                    continue;
                }

                parent.setLeftChild(node);
            } else {
                if (parent.getRightChild() != null) {
                    parent = parent.getRightChild();
                    continue;
                }

                parent.setRightChild(node);
            }

            break;
        }

        nodesCount++;
        return true;
    }

    public boolean contains(T data) {
        if (data == null || root == null) {
            return false;
        }

        MyTreeNode<T> node = root;

        while (true) {
            int compareResult = comparator.compare(data, node.getData());

            if (compareResult == 0) {
                return true;
            }

            if (compareResult < 0) {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();
                    continue;
                }
            } else {
                if (node.getRightChild() != null) {
                    node = node.getRightChild();
                    continue;
                }
            }

            return false;
        }
    }

    public boolean remove(T data) {
        if (data == null || root == null) {
            return false;
        }

        MyTreeNode<T> removedNodeParent = null;
        MyTreeNode<T> removedNode = root;
        boolean removedNodeSide = false; // false - удаляемый узел относится к левому поддереву, true - к правому

        while (true) {
            int compareResult = comparator.compare(data, removedNode.getData());

            if (compareResult == 0) {
                break;
            }

            if (compareResult < 0) {
                if (removedNode.getLeftChild() != null) {
                    removedNodeParent = removedNode;
                    removedNode = removedNode.getLeftChild();
                    removedNodeSide = false;
                    continue;
                }
            } else {
                if (removedNode.getRightChild() != null) {
                    removedNodeParent = removedNode;
                    removedNode = removedNode.getRightChild();
                    removedNodeSide = true;
                    continue;
                }
            }

            removedNode = null;
            break;
        }

        if (removedNode == null) {
            return false;
        }

        /*Если в строке 144 вместо такого условия использовать removedNode == root, то в строке 150 будет warning,
          что возможен NullPointerExeption. Такой же warning будет во всех ветках ниже, где вместо условия
          removedNodeParent == null будет использовано removedNode == root.
          Не могу понять - почему?
          Ведь если removedNode != root, его родитель никак не может быть null (он получит ссылку на узел в цикле выше)
          Родитель может остаться null лишь в одном случае - если у дерева есть только корень и данные в нем не равны
          удаляемым (то есть будут проверены условия в строках 106, 110, 111 или 118, они все дадут false, отработаю строки 126 и 127).
          Но тогда код выйдет из метода еще на строке 131.
          Для примера оставил warning в стоке 160*/
        if (removedNode.getLeftChild() == null && removedNode.getRightChild() == null) {
            if (removedNodeParent == null) {
                root = null;
            } else {
                if (removedNodeSide) {
                    removedNodeParent.setRightChild(null);
                } else {
                    removedNodeParent.setLeftChild(null);
                }
            }
        } else if (removedNode.getLeftChild() == null) {
            if (removedNode == root) { // чтобы пропал warning, условие нужно изменить на removedNodeParent == null
                root = root.getRightChild();
            } else {
                if (removedNodeSide) {
                    removedNodeParent.setRightChild(removedNode.getRightChild());
                } else {
                    removedNodeParent.setLeftChild(removedNode.getRightChild()); // warning оставил для примера
                }
            }
        } else if (removedNode.getRightChild() == null) {
            if (removedNodeParent == null) {
                root = root.getLeftChild();
            } else {
                if (removedNodeSide) {
                    removedNodeParent.setRightChild(removedNode.getLeftChild());
                } else {
                    removedNodeParent.setLeftChild(removedNode.getLeftChild());
                }
            }
        } else {
            MyTreeNode<T> minNode = removedNode.getRightChild();
            MyTreeNode<T> minNodeParent = removedNode;
            boolean minNodeSide = true;

            while (true) {
                if (minNode.getLeftChild() != null) {
                    minNodeParent = minNode;
                    minNode = minNode.getLeftChild();
                    minNodeSide = false;
                    continue;
                }

                break;
            }

            if (minNode.getRightChild() == null) {
                if (minNodeSide) {
                    minNodeParent.setRightChild(null);
                } else {
                    minNodeParent.setLeftChild(null);
                }
            } else {
                if (minNodeSide) {
                    minNodeParent.setRightChild(minNode.getRightChild());
                } else {
                    minNodeParent.setLeftChild(minNode.getRightChild());
                }
            }

            minNode.setLeftChild(removedNode.getLeftChild());
            minNode.setRightChild(removedNode.getRightChild());

            if (removedNodeParent == null) {
                root = minNode;
            } else {
                if (removedNodeSide) {
                    removedNodeParent.setRightChild(minNode);
                } else {
                    removedNodeParent.setLeftChild(minNode);
                }
            }
        }

        nodesCount--;
        return true;
    }

    public void breadthFirstIteration(Consumer<T> consumer) {
        checkForNull(consumer);

        Deque<MyTreeNode<T>> queue = new ArrayDeque<>(nodesCount);

        if (root != null) {
            queue.add(root);
        }

        MyTreeNode<T> node;

        while (!queue.isEmpty()) {
            node = queue.remove();
            consumer.accept(node.getData());

            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }

            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

    public void depthFirstIteration(Consumer<T> consumer) {
        checkForNull(consumer);

        Deque<MyTreeNode<T>> stack = new ArrayDeque<>(nodesCount);

        if (root != null) {
            stack.push(root);
        }

        MyTreeNode<T> topNode;

        while (!stack.isEmpty()) {
            topNode = stack.pop();
            consumer.accept(topNode.getData());

            if (topNode.getRightChild() != null) {
                stack.push(topNode.getRightChild());
            }

            if (topNode.getLeftChild() != null) {
                stack.push(topNode.getLeftChild());
            }
        }
    }

    public void recurseDepthFirstIteration(Consumer<T> consumer) {
        checkForNull(consumer);

        if (root != null) {
            visitNodes(root, consumer);
        }
    }

    private void visitNodes(MyTreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeftChild() != null) {
            visitNodes(node.getLeftChild(), consumer);
        }

        if (node.getRightChild() != null) {
            visitNodes(node.getRightChild(), consumer);
        }
    }

    private static void checkForNull(Object o) {
        if (o == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }
    }
}