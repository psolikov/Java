package ru.spbau.solikov.hw1.test;

import ru.spbau.solikov.hw1.src.List;

/**
 * Class for testing all the List functions
 */

public class ListTest {
    public static void main(String[] args) {
        List l = new List();

        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }

        for (List.Node node = l.getHead(); node != null; node = node.getNext()) {
            System.out.println(node.getKey());
        }

        System.out.println();

        System.out.println(l.find("5") + " - found key '5'.");
        l.delete("5");
        System.out.println(l.delete("5") + " - deleted key '5'.");

        System.out.println();

        l.clear();
        System.out.print(l.getHead() == null);
        System.out.println(" - cleared the list.");
    }
}
