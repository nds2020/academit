package ru.academit.nikitinds.tree;


import java.util.*;

public class MyTree<T> {
    private final Comparator<T> comparator;
    private MyTreeNode<T> root;
    private int nodesCount;

    public MyTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public MyTree(MyTreeNode<T> root, Comparator<T> comparator) {
        this.root = root;
        this.comparator = comparator;
        nodesCount++;
    }

    public int size() {
        return nodesCount;
    }

    public boolean add(MyTreeNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

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

        node.setParent(parent);
        nodesCount++;
        return true;
    }

    public boolean contains(T data) {
        if (data == null || root == null) {
            return false;
        }

        return findNode(data) != null;
    }

    private MyTreeNode<T> findNode(T data) {
        MyTreeNode<T> node = root;

        while (true) {
            if (comparator.compare(data, node.getData()) == 0) {
                return node;
            }

            if (comparator.compare(data, node.getData()) < 0) {
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

            return null;
        }
    }

    public boolean remove(T data) {
        if (data == null || root == null) {
            return false;
        }

        MyTreeNode<T> removedNode = findNode(data);

        if (removedNode == null) {
            return false;
        }

        if (removedNode.getLeftChild() == null && removedNode.getRightChild() == null) {
            if (removedNode == root) {
                root = null;
            } else {
                if (comparator.compare(data, removedNode.getParent().getData()) < 0) {
                    removedNode.getParent().setLeftChild(null);
                } else {
                    removedNode.getParent().setRightChild(null);
                }
            }
        } else if (removedNode.getLeftChild() == null) {
            if (removedNode == root) {
                root = root.getRightChild();
            } else {
                if (comparator.compare(data, removedNode.getParent().getData()) < 0) {
                    removedNode.getParent().setLeftChild(removedNode.getRightChild());
                } else {
                    removedNode.getParent().setRightChild(removedNode.getRightChild());
                }
            }
            removedNode.getRightChild().setParent(removedNode.getParent());
        } else if (removedNode.getRightChild() == null) {
            if (removedNode == root) {
                root = root.getLeftChild();
            } else {
                if (comparator.compare(data, removedNode.getParent().getData()) < 0) {
                    removedNode.getParent().setLeftChild(removedNode.getLeftChild());
                } else {
                    removedNode.getParent().setRightChild(removedNode.getLeftChild());
                }
            }
            removedNode.getLeftChild().setParent(removedNode.getParent());
        } else {
            MyTreeNode<T> minNode = removedNode.getRightChild();

            while (true) {
                if (minNode.getLeftChild() != null) {
                    minNode = minNode.getLeftChild();
                    continue;
                }

                break;
            }

            if (minNode.getRightChild() == null) {
                if (comparator.compare(data, minNode.getParent().getData()) < 0) {
                    minNode.getParent().setLeftChild(null);
                } else {
                    minNode.getParent().setRightChild(null);
                }
            } else {
                if (comparator.compare(data, minNode.getParent().getData()) < 0) {
                    minNode.getParent().setLeftChild(minNode.getRightChild());
                } else {
                    minNode.getParent().setRightChild(minNode.getRightChild());
                }

                minNode.getRightChild().setParent(minNode.getParent());
            }

            minNode.setLeftChild(removedNode.getLeftChild());
            minNode.setRightChild(removedNode.getRightChild());
            minNode.setParent(removedNode.getParent());

            if (removedNode == root) {
                root = minNode;
            } else {
                if (comparator.compare(data, removedNode.getParent().getData()) < 0) {
                    removedNode.getParent().setLeftChild(minNode);
                } else {
                    removedNode.getParent().setRightChild(minNode);
                }
            }
        }

        nodesCount--;
        return true;
    }

    public ArrayList<T> toArrayListByBreadthFirstIteration() {
        ArrayList<T> arrayList = new ArrayList<>(nodesCount);

        Queue<MyTreeNode<T>> nodes = new LinkedList<>();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            if (nodes.element().getLeftChild() != null) {
                nodes.add(nodes.element().getLeftChild());
            }

            if (nodes.element().getRightChild() != null) {
                nodes.add(nodes.element().getRightChild());
            }

            arrayList.add(nodes.remove().getData());
        }

        return arrayList;
    }

    public ArrayList<T> toArrayListByDepthFirstIteration() {
        ArrayList<T> arrayList = new ArrayList<>(nodesCount);

        Stack<MyTreeNode<T>> nodes = new Stack<>();

        nodes.push(root);
        MyTreeNode<T> topNode;

        while (!nodes.empty()) {
            topNode = nodes.pop();
            arrayList.add(topNode.getData());

            if (topNode.getRightChild() != null) {
                nodes.push(topNode.getRightChild());
            }

            if (topNode.getLeftChild() != null) {
                nodes.push(topNode.getLeftChild());
            }
        }

        return arrayList;
    }

    public ArrayList<T> toArrayListByRecurseDepthFirstIteration() {
        ArrayList<T> arrayList = new ArrayList<>(nodesCount);

        visitNodes(root, arrayList);
        return arrayList;
    }

    private void visitNodes(MyTreeNode<T> node, ArrayList<T> arrayList) {
        arrayList.add(node.getData());

        if (node.getLeftChild() != null) {
            visitNodes(node.getLeftChild(), arrayList);
        }

        if (node.getRightChild() != null) {
            visitNodes(node.getRightChild(), arrayList);
        }
    }
}