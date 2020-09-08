package ru.academit.nikitinds.hash_table_main;

import ru.academit.nikitinds.hash_table.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Number> hashTable = new MyHashTable<>();

        System.out.println("Создали пустую хэш-таблицу: " + Arrays.toString(hashTable.toArray()));

        hashTable.add(15);
        hashTable.add(16);
        hashTable.add(18);
        System.out.println("Добавили элементы в таблицу: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + hashTable.size());
        System.out.println();

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        hashTable.addAll(arrayList);
        hashTable.addAll(arrayList);
        System.out.println("Дважды добавили в таблицу все элементы из списка " + arrayList + ". Таблица теперь содержит элементы: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + hashTable.size());
        System.out.println();

        int element = 20;
        if (hashTable.contains(element)) {
            System.out.println("Таблица содержит элемент " + element);
        } else {
            System.out.println("Таблица не содержит элемент " + element);
        }

        arrayList.add(8);
        if (hashTable.containsAll(arrayList)) {
            System.out.println("Таблица содержит все элементы списка " + arrayList);
        } else {
            System.out.println("Таблица не содержит все элементы списка " + arrayList);
        }

        element = 4;
        hashTable.remove(element);
        System.out.println("Удалили из таблицы элемент " + element + ". Теперь таблица содержит элементы: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + hashTable.size());
        System.out.println();

        hashTable.removeAll(arrayList);
        System.out.println("Удалили из таблицы все элементы списка " + arrayList + ". Теперь таблица содержит элементы: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + hashTable.size());
        System.out.println();

        hashTable.retainAll(arrayList);
        System.out.println("Оставили в таблице только те элементы, которые есть в списке " + arrayList + ". Теперь таблица содержит элементы: " + Arrays.toString(hashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + hashTable.size());
        System.out.println();

        if (hashTable.isEmpty()) {
            System.out.println("Таблица сейчас пустая");
        } else {
            System.out.println("Таблица сейчас непустая");
        }

        System.out.println();

        MyHashTable<Integer> oneMoreHashTable = new MyHashTable<>(3);

        oneMoreHashTable.addAll(arrayList);
        System.out.println("Создали еще одну таблицу и добавили в нее все элементы списка " + arrayList + ". Теперь таблица содержит элементы: " + Arrays.toString(oneMoreHashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + oneMoreHashTable.size());
        System.out.println();

        oneMoreHashTable.clear();
        System.out.println("Очистили таблицу: " + Arrays.toString(oneMoreHashTable.toArray()));
        System.out.println("Количество элементов в таблице равно " + oneMoreHashTable.size());
        System.out.println();
    }
}