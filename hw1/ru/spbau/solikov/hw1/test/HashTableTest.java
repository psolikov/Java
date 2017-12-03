package ru.spbau.solikov.hw1.src.primitive.test;

import ru.spbau.solikov.hw1.src.HashTable;

/**
 *  Class for testing all the HashTable functions
 */

public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        for (int i = 0; i < 100; i++){
            hashTable.put(String.valueOf(i), String.valueOf(i * i));
        }

        System.out.println(hashTable.size() + " - size of hashtable (must be 100)");

        System.out.println();

        System.out.println(hashTable.get("10") + " - square of 10 (must be 100)");

        System.out.println();

        System.out.println(hashTable.put("10", "200") + " - changed square of 10 (must have been 100)");

        System.out.println();

        System.out.println(hashTable.contains("10") + " - contains '10' (must be true)");

        System.out.println();

        System.out.println(hashTable.remove("10") + " - removed '10'");

        System.out.println();

        hashTable.clear();

        System.out.println(hashTable.size() + " - size of hashtable (must be 0)");
    }
}