package ru.academit.nikitinds.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final LinkedList<E>[] rows;
    private int elementsCount;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        rows = (LinkedList<E>[]) new LinkedList[17];
    }

    public MyHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть больше 0");
        }

        //noinspection unchecked
        rows = (LinkedList<E>[]) new LinkedList[size];
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public boolean contains(Object o) {
        int oHash = getHashCode(o);

        if (rows[oHash] == null) {
            return false;
        }

        return rows[oHash].contains(o);
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
    public Object[] toArray() {
        Object[] result = new Object[elementsCount];

        int index = 0;

        for (E element : this) {
            result[index++] = element;
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < elementsCount) {
            a = (T[]) Arrays.copyOf(a, elementsCount, a.getClass());
        }

        int index = 0;

        for (E element : this) {
            a[index++] = (T) element;
        }

        if (a.length > elementsCount) {
            a[elementsCount] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int eHash = getHashCode(e);

        if (rows[eHash] == null) {
            rows[eHash] = new LinkedList<>();
        }

        elementsCount++;
        modCount++;
        return rows[eHash].add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkForNull(c);

        if (c.size() == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int oHash = getHashCode(o);

        if (rows[oHash] != null) {
            if (rows[oHash].remove(o)) {
                elementsCount--;
                modCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkForNull(c);

        if (c.size() == 0) {
            return false;
        }

        boolean hasRemove = false;

        for (Object o : c) {
            int oHash = getHashCode(o);

            if (rows[oHash] != null) {
                Iterator<E> iterator = rows[oHash].iterator();

                while (iterator.hasNext()) {
                    if (Objects.equals(iterator.next(), o)) {
                        iterator.remove();
                        elementsCount--;
                        modCount++;
                        hasRemove = true;
                    }
                }
            }
        }

        return hasRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkForNull(c);

        if (elementsCount == 0) {
            return false;
        }

        if (c.size() == 0) {
            clear();
            return true;
        }

        boolean hasRemove = false;

        for (LinkedList<E> row : rows) {
            if (row != null) {
                Iterator<E> iterator = row.iterator();

                while (iterator.hasNext()) {
                    if (!c.contains(iterator.next())) {
                        iterator.remove();
                        elementsCount--;
                        modCount++;
                        hasRemove = true;
                    }
                }
            }
        }

        return hasRemove;
    }

    @Override
    public void clear() {
        Arrays.fill(rows, null);
        elementsCount = 0;
        modCount++;
    }

    private int getHashCode(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % rows.length);
    }

    private static void checkForNull(Object c) {
        if (c == null) {
            throw new NullPointerException("Коллекция-аргумент не должна быть null");
        }
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentFilledRowIndex = -1;
        private int visitedElementsCount = 0;
        private Iterator<E> rowIterator;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return visitedElementsCount < elementsCount;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Таблица была изменена");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В таблице больше нет элементов");
            }

            if (currentFilledRowIndex == -1 || !rowIterator.hasNext()) {
                while (currentFilledRowIndex < rows.length) {
                    currentFilledRowIndex++;

                    if (rows[currentFilledRowIndex] != null && rows[currentFilledRowIndex].size() != 0) {
                        rowIterator = rows[currentFilledRowIndex].iterator();
                        break;
                    }
                }
            }

            visitedElementsCount++;
            return rowIterator.next();
        }
    }
}