package ru.spbau.solikov.src;

import java.util.Iterator;

/**
 * Implementation of single-linked list that stores Nodes of keys and data
 */
public class List<K, V> {

    public int size() {
        return size;
    }

    /**
     * Class that stores keys and data and has link for next Node
     */
    private class Node<K, V> {

        private Node<K, V> next;

        private K key;

        private V data;

        public Node(Node<K, V> n, K k, V d) {
            next = n;
            key = k;
            data = d;
        }
    }

    private Node<K, V> head;

    private Node<K, V> tail;

    private int size = 0;

    public Node<K, V> getHead() {
        return head;
    }

    /**
     * Adds a pair of key and data in List
     *
     * @param key
     * @param data
     */
    public void insert(K key, V data) {
        if (head != null) {
            tail.next = new Node<>(null, key, data);
            size++;
            tail = tail.next;
        } else {
            tail = head = new Node<>(null, key, data);
        }
    }

    /**
     * Checks if the list does not contain any elements
     *
     * @return boolean - true if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Searches for data by key in List
     *
     * @param key
     * @return data or null if not found
     */
    public V find(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    public K getHeadsKey() {
        return head.key;
    }

    public V getHeadsData() {
        return head.data;
    }

    /**
     * Deletes a data stored by key in List
     *
     * @param key
     * @return data that was deleted
     */
    public V delete(K key) {
        if (head == null) {
            return null;
        }

        if (head.key.equals(key)) {
            V deleted = head.data;
            head = head.next;
            size--;
            return deleted;
        }

        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                V deleted = current.next.data;
                current.next = current.next.next;
                size--;
                return deleted;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Clears the List
     */
    public void clear() {
        head = null;
        tail = null;
    }

    public Iterator getIterator() {
        return new ListIterator();
    }
}
