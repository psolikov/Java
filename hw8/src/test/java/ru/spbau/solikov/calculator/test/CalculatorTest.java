package ru.spbau.solikov.calculator.test;

import org.junit.Test;
import ru.spbau.solikov.calculator.src.Calculator;
import ru.spbau.solikov.calculator.src.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Mock tests for Calculator class.
 */
@SuppressWarnings("unchecked")
public class CalculatorTest {

    @Test
    public void testCompute() {
        Stack<Double> mockNumbers = mock(Stack.class);
        Stack<Character> mockOperators = mock(Stack.class);

        when(mockNumbers.pop()).thenReturn(7.0).thenReturn(3.0).thenReturn(10.0);

        when(mockNumbers.peek()).thenReturn(5.0);

        Calculator calculator = new Calculator(mockNumbers, mockOperators);

        String expressionInRPN = "37+2/";
        assertEquals(5.0, calculator.compute(expressionInRPN), 0);

        verify(mockNumbers, times(4)).push(anyDouble());
    }

    @Test
    public void testToRPN() {
        Stack<Double> mockNumbers = mock(Stack.class);
        Stack<Character> mockOperators = mock(Stack.class);

        when(mockOperators.empty()).thenReturn(true).thenReturn(false).thenReturn(true);

        when(mockOperators.peek()).thenReturn('+');

        when(mockOperators.pop()).thenReturn('+');

        String expression = "10+2";
        assertEquals( "10;2+", new Calculator(mockNumbers,
                mockOperators).toRPN(expression));

        verify(mockOperators, times(1)).push(anyChar());
        verifyZeroInteractions(mockNumbers);
    }
}
