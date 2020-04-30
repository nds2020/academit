package ru.academit.nikitinds.singlylinkedlist;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListElement<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.data;
    }

    public T get(int index) {
        checkIndex(index);
        return findElement(index).data;
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListElement<T> e = findElement(index);

        T oldData = e.data;
        e.data = data;
        return oldData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListElement<T> previous = findElement(index - 1);

        T removedData = previous.next.data;
        previous.next = previous.next.next;
        count--;
        return removedData;
    }

    public void addFirst(T data) {
        head = new ListElement<>(data, head);
        count++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + count + " включительно");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListElement<T> previous = findElement(index - 1);

        previous.next = new ListElement<>(data, previous.next);
        count++;
    }

    public boolean remove(T data) {
        if (data == null) {
            for (ListElement<T> e = head, prev = null; e != null; prev = e, e = e.next) {
                if (e.data == null) {
                    if (prev == null) {
                        removeFirst();
                    } else {
                        prev.next = e.next;
                        count--;
                    }

                    return true;
                }
            }
        } else {
            for (ListElement<T> e = head, prev = null; e != null; prev = e, e = e.next) {
                if (data.equals(e.data)) {
                    if (prev == null) {
                        removeFirst();
                    } else {
                        prev.next = e.next;
                        count--;
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T removedData = head.data;
        head = head.next;
        count--;
        return removedData;
    }

    public void revert() {
        ListElement<T> current = head;
        ListElement<T> previous = null;

        while (current.next != null) {
            head = current.next;
            current.next = previous;
            previous = current;
            current = head;
        }

        head.next = previous;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        if (head != null) {
            newList.addFirst(head.data);

            for (ListElement<T> src = head.next, dest = newList.head; src != null; dest = dest.next, src = src.next) {
                dest.next = new ListElement<>(src.data, dest.next);
                newList.count++;
            }
        }

        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вышел за границы индексов списка. Размер списка: " + count);
        }
    }

    private ListElement<T> findElement(int index) {
        ListElement<T> e = head;

        for (int i = 0; i < index; i++) {
            e = e.next;
        }

        return e;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        if (head != null) {
            ListElement<T> e = head;

            for (; e.next != null; e = e.next) {
                stringBuilder.append(e.data).append(", ");
            }

            stringBuilder.append(e.data);
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static class ListElement<T> {
        private T data;
        private ListElement<T> next;

        ListElement(T data, ListElement<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}