package ru.spbau.solikov.hw1.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.hw1.src.List;

import static org.junit.Assert.*;

/**
 * Test class for class List that implements single-linked list
 */
public class ListTest {

    private List l;

    /**
     * Constructs new list for every test
     */
    @Before
    public void setUp() {
        l = new List();
    }

    /**
     * Tests if 'isEmpty' on empty list returns true
     */
    @Test
    public void testIsEmptyOfEmptyList() {
        assertEquals(true, l.isEmpty());
    }

    /**
     * Tests if 'insert' on empty list inserts the right data
     */
    @Test
    public void testInsert() {
        l.insert("SPbAU", "Java");
        assertEquals("Java", l.getHeadsData());
    }

    /**
     * Tests if many 'insert's insert the right data
     */
    @Test
    public void testManyInserts() {
        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }

        int number = 0;
        while (!l.isEmpty()) {
            assertEquals(Integer.toString(number), l.getHeadsData());
            number++;
            l.delete(l.getHeadsKey());
        }
    }

    /**
     * Tests if 'find' on empty list returns null
     */
    @Test
    public void testFindNonexistingElement() {
        assertEquals(null, l.find("Something"));
    }

    /**
     * Tests if 'find' on one-element list returns right value
     */
    @Test
    public void testFindExistingElement() {
        l.insert("SPbAU", "Java");
        assertEquals("Java", l.find("SPbAU"));
    }

    /**
     * Tests if 'find' on many-element list returns right value
     */
    @Test
    public void testFindExistingElementManyElements() {
        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }
        l.insert("SPbAU", "Java");
        assertEquals("Java", l.find("SPbAU"));
    }

    /**
     * Tests if 'delete' on empty list does nothing
     */
    @Test
    public void testDeleteElementFromEmptyList() {
        l.delete("SomeKey");
        assertEquals(true, l.isEmpty());
    }

    /**
     * Tests if 'delete' nonexisting element on one-element list does nothing
     */
    @Test
    public void testDeleteNonexistingElement() {
        l.insert("1", "2");
        l.delete("5");
        assertNotEquals(true, l.isEmpty());
    }

    /**
     * Tests if 'delete' from one-element list truly deletes element
     */
    @Test
    public void testDeleteExistingElement() {
        l.insert("SPbAU", "Java");
        l.delete("SPbAU");
        assertEquals(true, l.isEmpty());
    }

    /**
     * Tests if 'clear' deletes all elements from many-element list
     */
    @Test
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }
        l.clear();
        assertEquals(true, l.isEmpty());
    }
}