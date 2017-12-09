package ru.spbau.solikov.calculator.src;

import java.util.EmptyStackException;

/**
 * Implementation of generic stack data structure using pointers.
 *
 * @param <T> type of elements stored inside
 */
public class Stack<T> {
    private Node<T> head = null;

    /**
     * Tests if this stack is empty.
     *
     * @return <code>true</code> if and only if this stack contains no items;
     * <code>false</code> otherwise.
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty.
     */
    public T peek() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }

        return head.value;
    }

    /**
     * Removes the object at the top of this stack and returns that object
     * as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException if this stack is empty.
     */
    public T pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }

        T top = head.value;
        head = head.parent;

        return top;
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param value the value to be pushed onto this stack.
     * @return the <code>value</code> argument.
     */
    public T push(T value) {
        head = new Node<T>(value, head);

        return value;
    }

    /**
     * Deletes everything from the stack.
     */
    public void clear() {
        head = null;
    }

    /**
     * Nested class that implements node of stack.
     */
    private static class Node<T> {
        private T value;
        private Node<T> parent = null;

        private Node(T value, Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
