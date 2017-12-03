package ru.spbau.solikov.functional.java.test;

import org.junit.Test;
import ru.spbau.solikov.functional.java.src.Function1;
import ru.spbau.solikov.functional.java.src.Function2;

import static org.junit.Assert.*;

/**
 * Class for testing the functional interface Function2
 * that represents a function of 2 arguments.
 */
public class TestFunction2 {
    @Test
    public void testComposeIntegerFunctions() {
        Function2<Integer, Integer, Integer> plus = (x, y) -> x + y;
        Function1<Integer, Integer> plus1 = x -> x + 1;
        assertEquals(Integer.valueOf(16), plus.compose(plus1).apply(10, 5));
    }

    @Test
    public void testComposeDoubleDoubleToStringToString() {
        Function2<Double, Double, String> convert = (x, y) -> String.valueOf(x / y);
        Function1<String, String> wrapper = x -> "(" + x + ")";
        assertEquals("(2.5)", convert.compose(wrapper).apply(5.0, 2.0));
    }

    @Test
    public void testBind1() {
        Function2<Integer, Integer, Integer> plus = (x, y) -> x + y;
        Function1<Integer, Integer> plus2 = y -> 2 + y;
        assertEquals(true,
                plus.bind1(2).apply(63423452).equals(plus2.apply(63423452)));
    }

    @Test
    public void testBind2() {
        Function2<Double, Double, Double> pow = Math::pow;
        Function1<Double, Double> pow2 = x -> x * x;
        assertEquals(true,
                pow.bind2(2.0).apply(63423452.0).equals(pow2.apply(63423452.0)));
    }

    @Test
    public void testCurry() {
        Function2<Double, Double, Double> pow = Math::pow;
        Function1<Double, Double> pow2 = x -> x * x * x;
        assertEquals(true,
                pow.curry(3.0).apply(123.0).equals(pow2.apply(123.0)));
    }
}
