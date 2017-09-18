package ru.spbau.solikov.hw1.src;

/**
 *  Implementation of hash table structure using separate chaining
 */
public class HashTable {

    /**
     *  Number used in hash function
     */
    private static final int DEFAULT_HASH_PRIME_NUMBER = 31;

    private List[] table;
    private int capacity;
    private int size;

    /**
     * Creates a HashTable of capacity 2
     */
    public HashTable() {
        capacity = 2;
        table = new List[2];
        size = 0;
    }

    /**
     * Returns number of keys
     *
     * @return size
     */
    public int size() {
        return size;
    }


    /**
     * Iterates on each list in HashTable and clears it, capacity is the same
     */
    public void clear() {
        for (List list : table) {
            if (list != null) {
                list.clear();
            }
        }
        size = 0;
    }


    /**
     * Hash function for obtaining a code from key
     *
     * @param key
     * @return hash code
     */
    private int getHash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (DEFAULT_HASH_PRIME_NUMBER * hash + key.charAt(i)) % capacity;
        }
        return hash;
    }


    /**
     * Returns data stored by key or null if not found
     *
     * @param key
     * @return data or null if not found
     */
    public String get(String key) {
        int hash = getHash(key);
        if (table[hash] == null) return null;
        return table[hash].find(key);
    }


    /**
     * Increases the capacity of HashTable by 2 times
     */
    private void increase() {
        List[] oldTable = table;
        table = new List[2 * capacity];
        capacity *= 2;

        for (List list : oldTable) {
            if (list != null) {
                List.Node node = list.getHead();
                while (node != null) {
                    String key = node.getKey();
                    int hash = getHash(key);
                    if (table[hash] == null) {
                        table[hash] = new List();
                    }
                    table[hash].insert(key, node.getData());
                    node = node.getNext();
                }
            }
        }
    }

    /**
     * Allows to add the pair of key and data into HashTable, if there's not enough space calls increase()
     *
     * @param key
     * @param data
     * @return data that was stored before by the same key or null if this space was empty
     */
    public String put(String key, String data) {
        int hash = getHash(key);
        if (table[hash] == null) {
            table[hash] = new List();
        }

        List current = table[hash];
        String deleted = current.delete(key);
        current.insert(key, data);

        if (deleted == null) {
            size++;
        }

        if (size >= capacity) {
            increase();
        }

        return deleted;
    }


    /**
     * Allows to remove data stored by key
     *
     * @param key
     * @return data stored by key
     */
    public String remove(String key) {
        int hash = getHash(key);

        if (table[hash] == null) {
            return null;
        }

        String deleted = table[hash].delete(key);
        if (deleted != null) {
            size--;
            return deleted;
        }

        return null;
    }


    /**
     * Checks whether HashTable contains some data by passed key
     *
     * @param key
     * @return data or null if the HashTable doesn't contain key
     */
    public boolean contains(String key) {
        return get(key) != null;
    }
}
