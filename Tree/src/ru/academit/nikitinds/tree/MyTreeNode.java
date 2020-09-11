package ru.academit.nikitinds.tree;

public class MyTreeNode<T> {
    private final T data;
    private MyTreeNode<T> leftChild;
    private MyTreeNode<T> rightChild;

    public MyTreeNode(T data) {
        this.data = data;
    }

    public MyTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public MyTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }
}