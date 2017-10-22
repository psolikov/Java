package ru.spbau.solikov.hw2.src;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Matrix of size N * N that could be printed as spiral and sorted by first elements in column
 */
public class Spiral {

    private final int size;
    private int matrix[][];

    /**
     * Generates matrix of given size
     * Transposes it in order to easily sort by columns
     *
     * @param N     odd integer
     * @param other matrix
     */
    public Spiral(int N, int other[][]) {
        size = N;
        matrix = new int[N][N];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[j][i] = other[i][j];
            }
        }
    }

    /**
     * Method for initializing matrix's fields from standard input
     * Transposes it in order to easily sort by columns
     */
    public void fill() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[j][i] = in.nextInt();
            }
        }
    }

    /**
     * Sorting the matrix by first element in column using custom comparator
     */
    public void sort() {
        if (size != 0) {
            Arrays.sort(matrix, (first, second) -> {
                Integer f = first[0];
                Integer s = second[0];
                return f.compareTo(s);
            });
        }
    }

    /**
     * Prints matrix's fields into standard output
     * Transposes matrix back to the original view
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Transposes matrix back to the original view
     * Prints matrix's fields into standard output by spiral order. Integer 'c' is the center of matrix
     * External 'for' loop implements the layer of matrix
     * 4 internal 'for' loops implement round way on sides of current rectangle
     */
    public void printSpiral(OutputStream os) {
        final int c = size / 2;
        try {
            PrintWriter pw = new PrintWriter(os, true);
            if (size > 0) {
                pw.print(matrix[c][c] + " ");
            }
            if (size > 1) {
                int x = c;
                int y = c;
                int layer = 0;
                for (int side = 2; side < size - layer; side++) {
                    y++;
                    pw.print(matrix[y][x] + " ");
                    for (int i = 1; i < side + layer; i++) {
                        pw.print(matrix[y][x - i] + " ");
                    }
                    x -= side - 1 + layer;
                    for (int i = 1; i < side + layer + 1; i++) {
                        pw.print(matrix[y - i][x] + " ");
                    }
                    y -= side + layer;
                    for (int i = 1; i < side + layer + 1; i++) {
                        pw.print(matrix[y][x + i] + " ");
                    }
                    x += side + layer;
                    for (int i = 1; i < side + layer + 1; i++) {
                        pw.print(matrix[y + i][x] + " ");
                    }
                    y += side + layer;
                    layer++;
                }
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.err.println("Exception occurred while writing to output stream : " + e.toString());
        }
    }
}