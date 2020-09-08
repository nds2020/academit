package ru.academit.nikitinds.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private final LinkedList<E>[] table;
    private int elementsCount;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[17];
    }

    public MyHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Аргумент size (размер таблицы) должен быть больше 0. Сейчас size равен " + size);
        }

        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[size];
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
        int index = getIndex(o);

        if (table[index] == null) {
            return false;
        }

        return table[index].contains(o);
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
            result[index] = element;
            index++;
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
            a[index] = (T) element;
            index++;
        }

        if (a.length > elementsCount) {
            a[elementsCount] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        elementsCount++;
        modCount++;
        return table[index].add(e);
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
        int index = getIndex(o);

        if (table[index] != null) {
            if (table[index].remove(o)) {
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

        boolean modified = false;
        boolean[] visitedLists = new boolean[table.length];

        for (Object o : c) {
            int index = getIndex(o);

            if (table[index] != null && table[index].size() != 0 && !visitedLists[index]) {
                int elementsInListCount = table[index].size();
                visitedLists[index] = true;

                if (table[index].removeAll(c)) {
                    elementsCount -= (elementsInListCount - table[index].size());
                    modCount++;
                    modified = true;
                }
            }
        }

        return modified;
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

        boolean modified = false;

        for (LinkedList<E> list : table) {
            if (list != null && list.size() != 0) {
                int elementsInListCount = list.size();

                if (list.retainAll(c)) {
                    elementsCount -= (elementsInListCount - list.size());
                    modCount++;
                    modified = true;
                }
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        elementsCount = 0;
        modCount++;
    }

    private int getIndex(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % table.length);
    }

    private static void checkForNull(Object c) {
        if (c == null) {
            throw new NullPointerException("Коллекция-аргумент не должна быть null");
        }
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int visitedElementsCount = 0;
        private Iterator<E> iterator;
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

            if (currentIndex == -1 || !iterator.hasNext()) {
                while (currentIndex < table.length) {
                    currentIndex++;

                    if (table[currentIndex] != null && table[currentIndex].size() != 0) {
                        iterator = table[currentIndex].iterator();
                        break;
                    }
                }
            }

            visitedElementsCount++;
            return iterator.next();
        }
    }
}