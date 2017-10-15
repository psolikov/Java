package ru.spbau.solikov.hw2.src.test;

import org.junit.Test;
import ru.spbau.solikov.hw2.src.Spiral;

import static org.junit.Assert.*;

/**
 * Class for testing Spiral. Creates the Spiral 3x3 and checks sorting function.
 */
public class SpiralTest {

    private int[][] elements = {
            {4, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    private Spiral s = new Spiral(3, elements);

    /**
     * Tests if matrix is equal to transposed input matrix
     */
    @Test
    public void testConstructor() {
        int[][] matrix = s.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                assertEquals(true, matrix[j][i] == elements[i][j]);
            }
        }
    }

    private int[][] elements2 = {
    };
    private Spiral s2 = new Spiral(0, elements2);

    /**
     * Checks if printSpiral does not crash on empty Spiral
     */
    @Test
    public void testPrintEmptySpiralToOutputStream() {
        s2.printSpiral(System.out);
    }

    private int[][] elements3 = {
            {10}
    };
    private Spiral s3 = new Spiral(1, elements3);

    @Test
    public void testPrintOneElementSpiralToOutputStream() {
        s3.printSpiral(System.out);
    }

    /**
     * Tests if matrix is sorted from left to right
     */
    @Test
    public void testSort() {
        s.sort();
        int[][] matrix = s.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            assertEquals(true, prev <= matrix[i][0]);
        }
    }

    /**
     * Checks if sort does not crash on empty Spiral
     */
    @Test
    public void testSortEmptySpiral() {
        s2.sort();
    }

    @Test
    public void testSortOneElementSpiral() {
        s3.sort();
        int[][] matrix = s3.getMatrix();
        int prev = matrix[0][0];
        assertEquals(10, prev);
    }

    private int[][] elements4 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
    };
    private Spiral s4 = new Spiral(4, elements4);

    @Test
    public void testIfSortDoesNotChangeSortedMatrix() {
        s4.sort();
        int[][] matrix = s4.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            assertEquals(true, prev <= matrix[i][0]);
        }
    }

    private int[][] elements5 = {
            {-1, -2, -3, -4},
            {-5, -6, -7, -8},
            {-9, -10, -11, -12},
            {-13, -14, -15, -16}
    };
    private Spiral s5 = new Spiral(4, elements5);

    @Test
    public void testSortOnNegativeNumbersMatrix() {
        s5.sort();
        int[][] matrix = s5.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            assertEquals(true, prev <= matrix[i][0]);
        }
    }
}