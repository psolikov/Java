package ru.spbau.solikov.hw2.src.test;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.hw2.src.Spiral;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;


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
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                assertEquals(true, matrix[j][i] == elements[i][j]);
            }
        }
    }

    /**
     * Tests if matrix is sorted from left to right
     */
    @Test
    public void testSort() {
        s.sort();
        int[][] matrix = s.getMatrix();
        int prev = matrix[0][0];
        for (int i = 1; i < matrix.length; i++){
            assertEquals(true, prev <= matrix[i][0]);
        }
    }

}