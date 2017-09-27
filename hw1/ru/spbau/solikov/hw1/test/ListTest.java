package ru.spbau.solikov.hw1.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.hw1.src.List;

import static org.junit.Assert.*;

public class ListTest {

    private List l;

    @Before
    public void setUp() {
        l = new List();
    }

    @Test
    public void testGetHeadOfEmptyList() {
        assertEquals(null, l.getHead());
    }

    @Test
    public void testInsert() {
        l.insert("SPbAU", "Java");
        assertEquals("Java", l.getHead().getData());
    }

    @Test
    public void testManyInserts() {
        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }

        int number = 0;
        for (List.Node n = l.getHead(); n != null; n = n.getNext()) {
            assertEquals(Integer.toString(number), n.getData());
            number++;
        }
    }

    @Test
    public void testFindNonexistingElement() {
        assertEquals(null, l.find("Something"));
    }

    @Test
    public void testFindExistingElement() {
        l.insert("SPbAU", "Java");
        assertEquals("Java", l.find("SPbAU"));
    }

    @Test
    public void testDeleteElementFromEmptyList() {
        l.delete("SomeKey");
        assertEquals(null, l.getHead());
    }

    @Test
    public void testDeleteNonexistingElement() {
        l.insert("1", "2");
        l.delete("5");
        assertNotEquals(null, l.getHead());
    }

    @Test
    public void testDeleteExistingElement() {
        l.insert("SPbAU", "Java");
        l.delete("SPbAU");
        assertEquals(null, l.getHead());
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            l.insert(String.valueOf(i), String.valueOf(i));
        }
        l.clear();
        assertEquals(null, l.getHead());
    }
}