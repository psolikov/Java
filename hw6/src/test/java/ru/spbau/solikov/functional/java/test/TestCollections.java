package ru.spbau.solikov.functional.java.test;

import org.junit.Test;
import ru.spbau.solikov.functional.java.src.Collections;
import ru.spbau.solikov.functional.java.src.Function1;
import ru.spbau.solikov.functional.java.src.Predicate;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for Collections, a convenient way to handle operations with collections by lambdas.
 */
public class TestCollections {
    @Test
    public void testMap() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayListMult2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
            arrayListMult2.add(i * 2);
        }
        Function1<Integer, Integer> f1 = x -> 2 * x;
        assertArrayEquals(arrayListMult2.toArray(),
                Collections.map(arrayList, f1).toArray());
    }

    @Test
    public void testFilter() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayListPositive = new ArrayList<>();
        for (int i = 0; Math.abs(i) < 10;
             i = (int) ((Math.abs(i) + 1) * (Math.pow(-1, i)))) {
            arrayList.add(i);
            if (i > 0) arrayListPositive.add(i);
        }
        Predicate<Integer> p = x -> x > 0;
        assertArrayEquals(arrayListPositive.toArray(),
                Collections.filter(arrayList, p).toArray());
    }

    @Test
    public void testTakeUnless() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
            if (i < 5) arrayList2.add(i);
        }
        Predicate<Integer> p = x -> x < 5;
        assertArrayEquals(arrayList2.toArray(),
                Collections.takeUnless(arrayList, p).toArray());
    }

    @Test
    public void testTakeWhile() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
            if (i < 5) arrayList2.add(i);
        }
        Predicate<Integer> p = x -> x >= 5;
        assertArrayEquals(arrayList2.toArray(),
                Collections.takeWhile(arrayList, p).toArray());
    }

    @Test
    public void testFoldl() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0 ; i < 10; i++){
            arrayList.add(i);
        }
        assertEquals(Integer.valueOf(45),
                Collections.foldl(arrayList, 0, (x, y) -> x + y));
    }

    @Test
    public void testFoldr() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1 ; i < 10; i++){
            arrayList.add(i);
        }
        assertEquals(Integer.valueOf(362880),
                Collections.foldr(arrayList, 1, (x, y) -> x * y));
    }
}
