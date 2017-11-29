package ru.spbau.solikov.calculator.src;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Console application that takes arithmetic expression, translates it into
 * "Reversed Polish Notation" and computes the answer using ru.spbau.solikov.calculator.src.Stack.
 */
public class Calculator {
    private Stack<Double> numbers = new Stack<>();
    private Stack<Character> operators = new Stack<>();

    /**
     * Default constructor that just creates new stacks.
     */
    public Calculator(){}

    /**
     * Constructs calculator with given stacks for numbers and operators to be computed.
     *
     * @param numbers   stack for storing numbers
     * @param operators stack for storing operators
     */
    public Calculator(@NotNull Stack<Double> numbers, @NotNull Stack<Character> operators) {
        this.numbers = numbers;
        this.operators = operators;
    }

    /**
     * Starts computing the result based on input string
     *
     * @param expression string to be computed in "Reversed Polish Notation"
     * @return result of computation
     */
    public Double compute(@NotNull String expression) throws IllegalArgumentException {
        numbers.clear();
        boolean terminal = true;
        boolean onlyNumber = true;
        int number = 0;
        for (char next : expression.toCharArray()) {
            if (Character.isDigit(next)) {
                number = (number * 10) + Character.digit(next, 10);
                terminal = false;
            } else {
                onlyNumber = false;
                if (!terminal) {
                    terminal = true;
                    numbers.push((double) number);
                    number = 0;
                }

                if (next == ';'){
                    continue;
                }

                if (!isOperator(next)) {
                    throw new IllegalArgumentException(
                            "Wrong operator. Pick '+', '-', '*' or '/'");
                }

                double x = numbers.pop();
                double y = numbers.pop();
                numbers.push(apply(x, y, next));
            }
        }

        return onlyNumber ? (double)number : numbers.peek();
    }

    /**
     * Translates given string with valid arithmetic expression into string that represents that
     * expression in "Reversed Polish Notation"
     *
     * @param expression to be translated
     * @return result of translation
     */
    public String toRPN(@NotNull String expression) {
        StringBuilder expressionInRPN = new StringBuilder();

        boolean isTerminal = true;
        for (int i = 0; i < expression.length(); i++) {
            char next = expression.charAt(i);
            if (Character.isDigit(next)) {
                isTerminal = false;
                expressionInRPN.append(next);
            } else {
                if (!isTerminal) {
                    isTerminal = true;
                    expressionInRPN.append(';');
                }
                if (next == '(') {
                    operators.push(next);
                } else if (next == ')') {
                    while (operators.peek() != '(') {
                        expressionInRPN.append(operators.pop());
                    }
                    operators.pop();
                } else {
                    int priority = operatorsPriority(next);
                    while (!operators.empty() &&
                            priority <= operatorsPriority(operators.peek())) {
                        expressionInRPN.append(operators.pop());
                    }
                    operators.push(next);
                }
            }
        }

        while (!operators.empty()){
            expressionInRPN.append(operators.pop());
        }
        return expressionInRPN.toString();
    }

    private int operatorsPriority(char operator) {
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '(':
                return 0;
            case ')':
                return 0;
            default:
                return 3;
        }
    }

    @Contract(pure = true)
    private boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    @NotNull
    @Contract(pure = true)
    private Double apply(double x, double y, char operator) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return y - x;
            case '*':
                return x * y;
            default:
                return y / x;
        }
    }
}
