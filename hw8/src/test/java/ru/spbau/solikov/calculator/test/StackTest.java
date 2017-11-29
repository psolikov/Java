package ru.spbau.solikov.calculator.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.calculator.src.Stack;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Class for testing implementation of stack data structure - Stack.
 */
public class StackTest {

    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testEmptyOnEmptyStack() {
        assertEquals(true, stack.empty());
    }

    @Test
    public void testEmptyOnNonEmptyStack() {
        stack.push(1);

        assertEquals(false, stack.empty());
    }

    @Test
    public void testPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekFromEmptyStack() {
        stack.peek();
    }

    @Test
    public void testPeekFromOneElementStack() {
        stack.push(1);

        assertEquals(1, stack.peek().intValue());
    }

    @Test
    public void testPeekFromMultiElementStack() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(666);

        assertEquals(666, stack.peek().intValue());
        stack.pop();
        assertEquals(30, stack.peek().intValue());
        stack.pop();
        assertEquals(20, stack.peek().intValue());
        stack.pop();
        assertEquals(10, stack.peek().intValue());
        stack.pop();
        assertEquals(true, stack.empty());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopFromEmptyStack() {
        stack.pop();
    }

    @Test
    public void testPopFromOneElementStack() {
        stack.push(100);

        assertEquals(100, stack.pop().intValue());
        assertEquals(true, stack.empty());
    }

    @Test
    public void testPopFromMultiElementStack() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(666);

        assertEquals(666, stack.pop().intValue());
        assertEquals(30, stack.pop().intValue());
        assertEquals(20, stack.pop().intValue());
        assertEquals(10, stack.pop().intValue());
        assertEquals(true, stack.empty());
    }

    @Test
    public void testClear() {
        stack.push(1);
        stack.clear();

        assertEquals(true, stack.empty());
    }
}
