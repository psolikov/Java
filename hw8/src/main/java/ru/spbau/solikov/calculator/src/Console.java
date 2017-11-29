package ru.spbau.solikov.calculator.src;

import java.util.Scanner;

/**
 * Class for reading expression from console and calculating it with Calculator class.
 */
public class Console {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        double result = calculator.compute(sc.nextLine());

        System.out.println(result);
    }
}
