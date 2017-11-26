package ru.spbau.solikov.functional.java.test;

import org.junit.Test;
import ru.spbau.solikov.functional.java.src.Predicate;

import java.lang.invoke.LambdaMetafactory;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

/**
 * Class for testing the functional interface Predicate
 * that represents a binary predicate of 2 argument.
 */
public class TestPredicate {
    @Test
    public void testNot() {
        Predicate<Integer> predicate = x -> x > 2;
        assertEquals(true, predicate.not().apply(1));
    }

    @Test
    public void testAndFails() {
        Predicate<Integer> predicate1 = x -> x > 2;
        Predicate<Integer> predicate2 = x -> x <= 2;
        assertEquals(false, predicate1.and(predicate2).apply(2));
    }

    @Test
    public void testAndSuccess() {
        Predicate<Integer> predicate1 = x -> x >= 2;
        Predicate<Integer> predicate2 = x -> x <= 2;
        assertEquals(true, predicate1.and(predicate2).apply(2));
    }

    @Test
    public void testOrFails() {
        Predicate<Integer> predicate1 = x -> x == 2;
        Predicate<Integer> predicate2 = x -> x > 2;
        assertEquals(false, predicate1.or(predicate2).apply(1));
    }

    @Test
    public void testOrSuccess() {
        Predicate<Integer> predicate1 = x -> x == 2;
        Predicate<Integer> predicate2 = x -> x > 2;
        assertEquals(true, predicate1.or(predicate2).apply(2));
    }

    @Test
    public void testAlwaysTrue(){
        Predicate<ForkJoinPool> predicate = Predicate.ALWAYS_TRUE();
        assertEquals(true,predicate.apply(new ForkJoinPool()));
    }

    @Test
    public void testAlwaysFalse(){
        Predicate<LambdaMetafactory> predicate = Predicate.ALWAYS_FALSE();
        assertEquals(false,predicate.apply(new LambdaMetafactory()));
    }
}
