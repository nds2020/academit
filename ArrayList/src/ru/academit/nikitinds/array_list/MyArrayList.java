package ru.academit.nikitinds.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int length;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше 0, сейчас она равна " + initialCapacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends E> c) {
        checkForNull(c);

        if (c.size() != 0) {
            //noinspection unchecked
            elements = (E[]) c.toArray();
            length = c.size();
        } else {
            //noinspection unchecked
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
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

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, length);

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
        checkIndexForAdd(index);

        if (length == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2 + 1);
        }

        System.arraycopy(elements, index, elements, index + 1, length - index);
        elements[index] = element;
        length++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkForNull(c);
        checkIndexForAdd(index);

        if (c.size() == 0) {
            return false;
        }

        if (length + c.size() > elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2 + c.size());
        }

        System.arraycopy(elements, index, elements, index + c.size(), length - index);
        int i = index;

        for (E element : c) {
            elements[i] = element;
            i++;
        }

        length += c.size();
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int oIndex = indexOf(o);

        if (oIndex < 0) {
            return false;
        }

        fastRemove(oIndex);
        length--;
        modCount++;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = elements[index];
        fastRemove(index);
        length--;
        modCount++;
        return removedElement;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkForNull(c);

        if (c.size() == 0) {
            return false;
        }

        boolean hasChange = false;

        for (int i = length - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                fastRemove(i);
                length--;
                modCount++;
                hasChange = true;
            }
        }

        return hasChange;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkForNull(c);

        if (length != 0 && c.size() == 0) {
            clear();
            return true;
        }

        boolean hasChange = false;

        for (int i = length - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                fastRemove(i);
                length--;
                modCount++;
                hasChange = true;
            }
        }

        return hasChange;
    }

    private void fastRemove(int index) {
        if (index < length - 1) {
            System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        }

        elements[length - 1] = null;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, length, null);
        length = 0;
        modCount++;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkForNull(c);

        for (Object o : c) {
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

    private static void checkForNull(Object o) {
        if (o == null) {
            throw new NullPointerException("Коллекция-аргумент не может быть null");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вышел за границы допустимых значений. Резмер списка равен " + length);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + length + " включительно");
        }
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В списке больше нет элементов");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }
}