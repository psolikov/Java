package ru.spbau.solikov.src;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Map {

    private static final int DEFAULT_HASH_PRIME_NUMBER = 31;
    private List[] table;
    private MySet mySet;
    private int capacity = 2;
    private int size = 0;

    /**
     * Creates a HashMap of default capacity
     */
    public HashMap() {
        table = new List[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        int hash = key.hashCode() % capacity;
        if (table[hash] == null) {
            return null;
        }
        return table[hash].find((K) key);
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
                while (!list.isEmpty()) {
                    Object key = list.getHeadsKey();
                    int hash = key.hashCode() % capacity;
                    if (table[hash] == null) {
                        table[hash] = new List();
                    }
                    table[hash].insert(key, list.getHeadsData());
                    list.delete(key);
                }
            }
        }
    }

    /**
     * Allows to add the pair of key and data into HashTable, if there's not enough space calls increase()
     *
     * @param key
     * @param value
     * @return data that was stored before by the same key or null if this space was empty
     */
    @Override
    public Object put(Object key, Object value) {
        int hash = key.hashCode() % capacity;
        if (table[hash] == null) {
            table[hash] = new List<>();
        }

        List current = table[hash];
        Object deleted = current.delete(key);
        current.insert(key, value);

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
    @Override
    public Object remove(Object key) {
        int hash = key.hashCode() % capacity;

        if (table[hash] == null) {
            return null;
        }

        Object deleted = table[hash].delete(key);
        if (deleted != null) {
            size--;
            return deleted;
        }

        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    /**
     * Iterates on each list in HashTable and clears it, capacity is the same
     */
    @Override
    public void clear() {
        for (List list : table) {
            if (list != null) {
                list.clear();
            }
        }
        size = 0;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return mySet;
    }
}
