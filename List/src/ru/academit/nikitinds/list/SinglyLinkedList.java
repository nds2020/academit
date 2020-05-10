package ru.academit.nikitinds.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListElement<T> head;
    private int count;

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

        ListElement<T> prev = findElement(index - 1);

        T removedData = prev.next.data;
        prev.next = prev.next.next;
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

        ListElement<T> prev = findElement(index - 1);

        prev.next = new ListElement<>(data, prev.next);
        count++;
    }

    public boolean remove(T data) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        if (Objects.equals(data, head.data)) {
            head = head.next;
            count--;
            return true;
        }

        for (ListElement<T> crt = head.next, prev = head; crt != null; prev = crt, crt = crt.next) {
            if (Objects.equals(data, crt.data)) {
                prev.next = crt.next;
                count--;
                return true;
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
        if (head == null || head.next == null) {
            return;
        }

        ListElement<T> crt = head;
        ListElement<T> prev = null;

        while (crt.next != null) {
            head = crt.next;
            crt.next = prev;
            prev = crt;
            crt = head;
        }

        head.next = prev;
    }

    public SinglyLinkedList<T> copy() {
        if (head == null) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        newList.head = new ListElement<>(head.data, null);

        for (ListElement<T> src = head.next, dest = newList.head; src != null; dest = dest.next, src = src.next) {
            dest.next = new ListElement<>(src.data, null);
        }

        newList.count = count;
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