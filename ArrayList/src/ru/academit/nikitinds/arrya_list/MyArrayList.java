package ru.academit.nikitinds.arrya_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] elements;
    private int length;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        //noinspection unchecked
        elements = (E[]) new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends E> c) {
        if (c.size() != 0) {
            //noinspection unchecked
            elements = (E[]) c.toArray();
            length = c.size();
        } else {
            //noinspection unchecked
            elements = (E[]) new Object[10];
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вышел за границы индексов списка. Размер списка " + length);
        }

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вышел за границы индексов списка. Размер списка " + length);
        }

        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public int size() {
        return length;
    }

    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    public void trimToSize() {
        if (length < elements.length) {
            elements = Arrays.copyOf(elements, length);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            a = Arrays.copyOf(a, length);
        }

        Object[] elements = this.elements;
        Object[] newA = a;
        System.arraycopy(elements, 0, newA, 0, length);

        if (a.length > length) {
            a[length] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        add(length, e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + length);
        }

        ensureCapacity(length + 1);

        if (index == length) {
            elements[length] = element;
        } else {
            System.arraycopy(elements, index, elements, index + 1, length - index);
            elements[index] = element;
        }

        length++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addAll(length, c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + length);
        }

        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(length + c.size());

        if (index != length) {
            System.arraycopy(elements, index, elements, index + c.size(), length - index);
        }

        Object[] addedCollection = c.toArray();
        Object[] elements = this.elements;
        System.arraycopy(addedCollection, 0, elements, index, addedCollection.length);
        length += addedCollection.length;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                fastRemove(i);
                length--;
                modCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вышел за границы индексов списка. Размер списка " + length);
        }

        E removedElement = elements[index];
        fastRemove(index);
        length--;
        modCount++;
        return removedElement;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return false;
        }

        boolean hasRemove = false;

        for (int i = length - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                fastRemove(i);
                length--;
                modCount++;
                hasRemove = true;
            }
        }

        return hasRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return false;
        }

        boolean hasRemove = false;

        for (int i = length - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                fastRemove(i);
                length--;
                modCount++;
                hasRemove = true;
            }
        }

        return hasRemove;
    }

    private void fastRemove(int index) {
        if (index < length - 1) {
            System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        }

        elements[length - 1] = null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            elements[i] = null;
        }

        length = 0;
        modCount++;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return false;
        }

        Object[] array = c.toArray();

        for (Object o : array) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }

        return stringBuilder.append(elements[length - 1]).append("]").toString();
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection ConstantConditions
        return null;
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int currentIndex = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В списке больше нет элементов");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }
}