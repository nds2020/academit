package ru.academit.nikitinds.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final LinkedList<E>[] rows;
    private int elementsCount;

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

        if (c.size() != 0) {
            for (Object o : c) {
                if (!contains(o)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[elementsCount];

        if (elementsCount != 0) {
            Iterator<E> tableIterator = new MyHashTableIterator();

            for (int i = 0; i < elementsCount; i++) {
                result[i] = tableIterator.next();
            }
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < elementsCount) {
            a = (T[]) Arrays.copyOf(a, elementsCount, a.getClass());
        }

        if (elementsCount != 0) {
            Iterator<E> tableIterator = new MyHashTableIterator();

            for (int i = 0; i < elementsCount; i++) {
                a[i] = (T) tableIterator.next();
            }
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
        return rows[eHash].add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null || c.size() == 0) {
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
    }

    private int getHashCode(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % rows.length);
    }

    private void checkForNull(Object c) {
        if (c == null) {
            throw new NullPointerException("Коллекция-аргумент не должна быть null");
        }
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentFilledRowIndex = -1;
        private Iterator<E> rowIterator;

        private boolean hasNextFilledRow(int currentFilledRowIndex) {
            return nextFilledRowIndex(currentFilledRowIndex) >= 0;
        }

        private int nextFilledRowIndex(int currentFilledRowIndex) {
            for (int i = currentFilledRowIndex + 1; i < rows.length; i++) {
                if (rows[i] != null && rows[i].size() != 0) {
                    return i;
                }
            }

            return -1;
        }

        @Override
        public boolean hasNext() {
            return elementsCount != 0 && (hasNextFilledRow(currentFilledRowIndex) || rowIterator.hasNext());
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В таблице больше нет элементов");
            }

            if (currentFilledRowIndex == -1 || !rowIterator.hasNext()) {
                currentFilledRowIndex = nextFilledRowIndex(currentFilledRowIndex);
                rowIterator = rows[currentFilledRowIndex].iterator();
            }

            return rowIterator.next();
        }
    }
}