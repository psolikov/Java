package ru.spbau.solikov.set.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ru.spbau.solikov.set.src.MySet;

/**
 * Class for testing the MySet class. Creates set of Integer and set of String and checks if
 * 'add', 'contains' and size work properly
 */
public class MySetTest {

    private MySet<String> mySetString;
    private MySet<Integer> mySetInt;

    @Before
    public void setUp() {
        mySetString = new MySet<>();
        mySetInt = new MySet<>();
    }

    @Test
    public void testSizeOfEmptyStringSet() {
        assertEquals(0, mySetString.size());
    }

    @Test
    public void testSizeOfEmptyIntSet() {
        assertEquals(0, mySetInt.size());
    }

    @Test
    public void testAddOneToIntSet() {
        mySetInt.add(1);
        assertEquals(1, mySetInt.size());
    }

    @Test
    public void testAddOneToStringSet() {
        mySetString.add("1");
        assertEquals(1, mySetString.size());
    }

    @Test
    public void testAddManyDifferentToIntSet() {
        for (int i = 0; i < 10; i++) {
            mySetInt.add(i);
        }
        assertEquals(10, mySetInt.size());
    }

    @Test
    public void testAddManyDifferentToStringSet() {
        for (int i = 0; i < 10; i++) {
            mySetString.add(String.valueOf(i));
        }
        assertEquals(10, mySetString.size());
    }

    @Test
    public void testAddManySimilarToStringSet() {
        for (int i = 0; i < 10; i++) {
            mySetString.add(String.valueOf(1));
        }
        assertEquals(1, mySetString.size());
    }

    @Test
    public void testAddManySimilarToIntSet() {
        for (int i = 0; i < 10; i++) {
            mySetInt.add(i);
        }
        assertEquals(10, mySetInt.size());
    }

    @Test
    public void testContainsOnEmptyStringSet() {
        assertEquals(false, mySetString.contains(String.valueOf(564)));
    }

    @Test
    public void testContainsOnNonEmptyStringSet() {
        mySetString.add("SPbAU");
        assertEquals(false, mySetString.contains(String.valueOf(564)));
    }

    @Test
    public void testContainsElementOfSetOnNonEmptyStringSet() {
        mySetString.add("SPbAU");
        assertEquals(true, mySetString.contains("SPbAU"));
    }

    @Test
    public void testContainsOnEmptyIntSet() {
        assertEquals(false, mySetInt.contains(564));
    }

    @Test
    public void testContainsOnNonEmptyIntSet() {
        mySetInt.add(100);
        assertEquals(false, mySetInt.contains(564));
    }

    @Test
    public void testContainsElementOfSetOnNonEmptyIntSet() {
        mySetInt.add(123);
        assertEquals(true, mySetInt.contains(123));
    }

}

