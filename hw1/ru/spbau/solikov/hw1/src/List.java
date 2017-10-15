package ru.spbau.solikov.hw1.src;

/**
 * Implementation of single-linked list that stores Nodes of keys and data
 */
public class List {

    /**
     * Class that stores keys and data and has link for next Node
     */
    private class Node {

        private Node next;
        private String key;
        private String data;

        public Node(Node n, String k, String d) {
            next = n;
            key = k;
            data = d;
        }
    }

    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    /**
     * Adds a pair of key and data in List
     *
     * @param key
     * @param data
     */
    public void insert(String key, String data) {
        if (head != null) {
            tail.next = new Node(null, key, data);
            tail = tail.next;
        } else {
            tail = head = new Node(null, key, data);
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
    public String find(String key) {
        Node current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    public String getHeadsKey() {
        return head.key;
    }

    public String getHeadsData() {
        return head.data;
    }

    /**
     * Deletes a data stored by key in List
     *
     * @param key
     * @return data that was deleted
     */
    public String delete(String key) {
        if (head == null) {
            return null;
        }

        if (head.key.equals(key)) {
            String deleted = head.data;
            head = head.next;
            return deleted;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                String deleted = current.next.data;
                current.next = current.next.next;
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
}
