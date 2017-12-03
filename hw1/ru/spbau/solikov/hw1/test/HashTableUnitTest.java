package ru.spbau.solikov.hw1.src.primitive.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.hw1.src.HashTable;

import static org.junit.Assert.*;

/**
 * Test class for HashTable with separate chaining
 */
public class HashTableTest {

    private HashTable table;

    /**
     * Constructs new hash table for every test
     */
    @Before
    public void setUp() {
        table = new HashTable();
    }

    /**
     * Tests if the size of empty table is 0
     */
    @Test
    public void testSizeOfEmptyHashTable() {
        assertEquals(0, table.size());
    }

    /**
     * Tests if 'put' puts the right key
     */
    @Test
    public void testPut() {
        table.put("27", "09");
        assertEquals(true, table.contains("27"));
    }

    /**
     * Tests if the size after many 'puts' is correct
     */
    @Test
    public void testSizeAfterPut() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(10, table.size());
    }

    /**
     * Tests if the size after 'put' in the same key is correct
     */
    @Test
    public void testSizeAfterPutExistingKey() {
        table.put("123", "321");
        table.put("123", "333");
        assertEquals(1, table.size());
    }

    /**
     * Tests if 'put' inserts new value instead of previous
     */
    @Test
    public void testPutExistingKey() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.put("4", "99");
        assertEquals("99", table.get("4"));
    }

    /**
     * Tests if 'size' on empty table is 0
     */
    @Test
    public void testClearEmpty() {
        table.clear();
        assertEquals(0, table.size());
    }

    /**
     * Tests if 'clear' works right on table with many keys
     */
    @Test
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.clear();
        assertEquals(0, table.size());
    }

    /**
     * Tests if 'get' from empty table returns null
     */
    @Test
    public void testGetFromEmpty() {
        assertEquals(null, table.get("Deadline"));
    }

    /**
     * Tests if 'get' from one-element table returns the right value
     */
    @Test
    public void testGetElementFromOneElementTable() {
        table.put("1", "2");
        assertEquals("2", table.get("1"));
    }

    /**
     * Tests if 'get' on many-element table returns the right value
     */
    @Test
    public void testGetElementFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals("5", table.get("5"));
    }

    /**
     * Tests if 'get' does not change the size of hash table
     */
    @Test
    public void testGetDoesNotChangeSize() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.get("5");
        assertEquals(10, table.size());
    }

    /**
     * Tests if 'remove' does nothing with table after removing from empty table
     */
    @Test
    public void testSizeAfterRemovingFromEmptyTable() {
        table.remove("123");
        assertEquals(0, table.size());
    }

    /**
     * Tests if 'remove' on empty table returns null
     */
    @Test
    public void testRemoveFromEmptyTable() {
        assertEquals(null, table.remove("123"));
    }

    /**
     * Tests if 'remove' on one-element table returns the right value
     */
    @Test
    public void testRemoveFromOneElementTable() {
        table.put("123", "321");
        assertEquals("321", table.get("123"));
    }

    /**
     * Tests if the size of table becomes zero after removing from one-element table
     */
    @Test
    public void testSizeAfterRemovingFromOneElementTable() {
        table.put("123", "321");
        table.remove("123");
        assertEquals(0, table.size());
    }

    /**
     * Tests if the size of hash table is not affected by removing element that does not exist
     */
    @Test
    public void testSizeAfterRemovingNonexistingFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.remove("100");
        assertEquals(10, table.size());
    }

    /**
     * Tests if size after removing existing element decreases
     */
    @Test
    public void testSizeAfterRemoveFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.remove("5");
        assertEquals(9, table.size());
    }

    /**
     * Tests if 'remove' from many-element table returns the right value
     */
    @Test
    public void testRemoveFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals("4", table.remove("4"));
    }

    /**
     * Tests if 'contains' returns false on empty table
     */
    @Test
    public void testContainsFromEmptyTable() {
        assertEquals(false, table.contains("some"));
    }

    /**
     * Tests if 'contains' returns true on element that exists in one-element table
     */
    @Test
    public void testContainsFromOneElementTable() {
        table.put("Wednesday", "27.09");
        assertEquals(true, table.contains("Wednesday"));
    }

    /**
     * Tests if 'contains' returns false on element that does not exist in one-element table
     */
    @Test
    public void testContainsNonexistingElementFromOneElementTable() {
        table.put("Wednesday", "27.09");
        assertEquals(false, table.contains("Sunday"));
    }

    /**
     * Tests if 'contains' returns true on many-element table
     */
    @Test
    public void testContainsManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(true, table.contains("4"));
    }

    /**
     * Tests if 'contains' returns false on elements that don't exist in many-element table
     */
    @Test
    public void testContainsNonexistingElementFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(false, table.contains("123"));
    }
}