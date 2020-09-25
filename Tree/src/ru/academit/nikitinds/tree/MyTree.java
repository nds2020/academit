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
        this.comparator = (comparator == null) ? Comparator.nullsFirst(Comparator.naturalOrder()) : Comparator.nullsFirst(comparator);
    }

    public MyTree(T data, Comparator<T> comparator) {
        this.root = new MyTreeNode<>(data);
        this.comparator = (comparator == null) ? Comparator.nullsFirst(Comparator.naturalOrder()) : Comparator.nullsFirst(comparator);
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
        if (root == null) {
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
        if (root == null) {
            return false;
        }

        MyTreeNode<T> removedNodeParent = null;
        MyTreeNode<T> removedNode = root;
        boolean belongRemovedNodeToRightSubtree = false;

        while (true) {
            int compareResult = comparator.compare(data, removedNode.getData());

            if (compareResult == 0) {
                break;
            }

            if (compareResult < 0) {
                if (removedNode.getLeftChild() != null) {
                    removedNodeParent = removedNode;
                    removedNode = removedNode.getLeftChild();
                    belongRemovedNodeToRightSubtree = false;
                    continue;
                }
            } else {
                if (removedNode.getRightChild() != null) {
                    removedNodeParent = removedNode;
                    removedNode = removedNode.getRightChild();
                    belongRemovedNodeToRightSubtree = true;
                    continue;
                }
            }

            removedNode = null;
            break;
        }

        if (removedNode == null) {
            return false;
        }

        if (removedNode.getLeftChild() == null && removedNode.getRightChild() == null) {
            if (removedNodeParent == null) {
                root = null;
            } else {
                if (belongRemovedNodeToRightSubtree) {
                    removedNodeParent.setRightChild(null);
                } else {
                    removedNodeParent.setLeftChild(null);
                }
            }
        } else if (removedNode.getLeftChild() == null) {
            if (removedNodeParent == null) {
                root = root.getRightChild();
            } else {
                if (belongRemovedNodeToRightSubtree) {
                    removedNodeParent.setRightChild(removedNode.getRightChild());
                } else {
                    removedNodeParent.setLeftChild(removedNode.getRightChild());
                }
            }
        } else if (removedNode.getRightChild() == null) {
            if (removedNodeParent == null) {
                root = root.getLeftChild();
            } else {
                if (belongRemovedNodeToRightSubtree) {
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
                if (belongRemovedNodeToRightSubtree) {
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

        if (root == null) {
            return;
        }

        Queue<MyTreeNode<T>> queue = new ArrayDeque<>(nodesCount);

        queue.add(root);
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

        if (root == null) {
            return;
        }

        Deque<MyTreeNode<T>> stack = new ArrayDeque<>(nodesCount);

        stack.push(root);
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