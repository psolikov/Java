package ru.spbau.solikov.hw2.src.test;

import org.junit.Test;
import ru.spbau.solikov.hw2.src.Spiral;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Writer;

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
    String correctOrderForS = "5 6 3 2 4 4 7 8 9 ";

    private int[][] elements2 = {
    };
    private Spiral s2 = new Spiral(0, elements2);
    String correctOrderForS2 = "";

    private int[][] elements3 = {
            {10}
    };
    private Spiral s3 = new Spiral(1, elements3);
    String correctOrderForS3 = "10 ";

    private int[][] elements5 = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };
    private Spiral s5 = new Spiral(5, elements5);


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

    @Test
    public void testPrintThreeElementSpiralToOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        s.printSpiral(byteArrayOutputStream);
        String output = byteArrayOutputStream.toString();
        assertEquals(output, correctOrderForS);
    }

    @Test
    public void testPrintNoElementSpiralToOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        s2.printSpiral(byteArrayOutputStream);
        String output = byteArrayOutputStream.toString();
        assertEquals(output, correctOrderForS2);
    }


    @Test
    public void testPrintOneElementSpiralToOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        s3.printSpiral(byteArrayOutputStream);
        String output = byteArrayOutputStream.toString();
        assertEquals(output, correctOrderForS3);
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

    @Test
    public void testIfSortDoesNotChangeSortedMatrix() {
        s5.sort();
        int[][] matrix = s5.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            assertEquals(true, prev <= matrix[i][0]);
        }
    }

    private int[][] elementsNegative5 = {
            {-1, -2, -3, -4},
            {-5, -6, -7, -8},
            {-9, -10, -11, -12},
            {-13, -14, -15, -16}
    };
    private Spiral sNegative5 = new Spiral(4, elementsNegative5);

    @Test
    public void testSortOnNegativeNumbersMatrix() {
        sNegative5.sort();
        int[][] matrix = sNegative5.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            assertEquals(true, prev <= matrix[i][0]);
        }
    }
}