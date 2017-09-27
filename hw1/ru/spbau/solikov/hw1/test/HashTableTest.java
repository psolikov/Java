package ru.spbau.solikov.hw1.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.hw1.src.HashTable;

import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable table;

    @Before
    public void setUp() {
        table = new HashTable();
    }

    @Test
    public void testSizeOfEmptyHashTable() {
        assertEquals(0, table.size());
    }

    @Test
    public void testPut() {
        table.put("27", "09");
        assertEquals(true, table.contains("27"));
    }

    @Test
    public void testSizeAfterPut() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(10, table.size());
    }

    @Test
    public void testSizeAfterPutExistingKey() {
        table.put("123", "321");
        table.put("123", "333");
        assertEquals(1, table.size());
    }

    @Test
    public void testPutExistingKey() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.put("4", "99");
        assertEquals("99", table.get("4"));
    }

    @Test
    public void testClearEmpty() {
        table.clear();
        assertEquals(0, table.size());
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.clear();
        assertEquals(0, table.size());
    }

    @Test
    public void testGetFromEmpty() {
        assertEquals(null, table.get("Deadline"));
    }

    @Test
    public void testGetElementFromOneElementTable() {
        table.put("1", "2");
        assertEquals("2", table.get("1"));
    }

    @Test
    public void testGetElementFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals("5", table.get("5"));
    }

    @Test
    public void testGetDoesNotChangeSize() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.get("5");
        assertEquals(10, table.size());
    }

    @Test
    public void testSizeAfterRemovingFromEmptyTable() {
        table.remove("123");
        assertEquals(0, table.size());
    }

    @Test
    public void testRemoveFromEmptyTable() {
        assertEquals(null, table.remove("123"));
    }

    @Test
    public void  testRemoveFromOneElementTable() {
        table.put("123", "321");
        assertEquals("321", table.get("123"));
    }

    @Test
    public void  testSizeAfterRemovingFromOneElementTable() {
        table.put("123", "321");
        table.remove("123");
        assertEquals(0, table.size());
    }

    @Test
    public void  testSizeAfterRemovingNonexistingFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.remove("100");
        assertEquals(10, table.size());
    }
    
    @Test
    public void testSizeAfterRemoveFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        table.remove("5");
        assertEquals(9, table.size());
    }

    @Test
    public void testRemoveFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals("4", table.remove("4"));
    }

    @Test
    public void testContainsFromEmptyTable() {
        assertEquals(false, table.contains("some"));
    }

    @Test
    public void testContainsFromOneElementTable() {
        table.put("Wednesday", "27.09");
        assertEquals(true, table.contains("Wednesday"));
    }

    @Test
    public void testContainsNonexistingElementFromOneElementTable() {
        table.put("Wednesday", "27.09");
        assertEquals(false, table.contains("Sunday"));
    }

    @Test
    public void testContainsManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(true, table.contains("4"));
    }

    @Test
    public void testContainsNonexistingElementFromManyElementTable() {
        for (int i = 0; i < 10; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(false, table.contains("123"));
    }
}