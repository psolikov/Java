package ru.spbau.solikov.functional.java.test;

import org.junit.Test;
import ru.spbau.solikov.functional.java.src.Function1;

import static org.junit.Assert.*;

/**
 * Class for testing the functional interface Function1
 * that represents a function of 1 argument.
 */
public class TestFunction1 {
    @Test
    public void testApplySquareToInteger() {
        Function1<Integer, Integer> function1 = x -> x * x;
        assertEquals(true, 4194304 == function1.apply(2048));
    }

    @Test
    public void testComposeFromIntegerToIntegerFunctions(){
        Function1<Integer, Integer> multiplyBy3 = x -> x * 3;
        Function1<Integer, Integer> square = x -> x * x;
        assertEquals(true, multiplyBy3.compose(square).apply(2) == 36);
    }

    @Test
    public void testComposeFromDoubleToDoubleToString() {
        Function1<Double, Double> sin = Math::sin;
        Function1<Double, String> print = x -> Double.toString(x);
        assertEquals("1.0", sin.compose(print).apply(Math.PI/2));
    }
}
