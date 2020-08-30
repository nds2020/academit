package ru.academit.nikitinds.tree;


public class MyTreeNode<T> {
    private final T data;
    private MyTreeNode<T> leftChild;
    private MyTreeNode<T> rightChild;
    private MyTreeNode<T> parent;

    public MyTreeNode(T data) {
        if (data == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

        this.data = data;
    }

    public MyTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public MyTreeNode<T> getRightChild() {
        return rightChild;
    }

    public MyTreeNode<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void setLeftChild(MyTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(MyTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setParent(MyTreeNode<T> parent) {
        this.parent = parent;
    }
 }