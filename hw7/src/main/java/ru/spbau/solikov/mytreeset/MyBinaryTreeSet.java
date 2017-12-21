package ru.spbau.solikov.mytreeset;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implementation of interface 'MyTreeSet' using simple binary tree.
 *
 * @param <E> type of values in the collection
 */
public class MyBinaryTreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {
    private boolean isDescending = false;
    private BST<E> tree = new BST<>();

    /**
     * Constructor without arguments means that set will be sorting elements by natural order.
     */
    public MyBinaryTreeSet() {
    }

    /**
     * Allows to change the comparator.
     *
     * @param comparator to be used as sorting comparator of stored values
     */
    public MyBinaryTreeSet(@NotNull Comparator<? super E> comparator) {
        tree = new BST<>(comparator);
    }

    /**
     * Tool for creating descending set from already existing one.
     *
     * @param other MyBinaryTreeSet to be reversed
     */
    public MyBinaryTreeSet(@NotNull MyBinaryTreeSet<E> other) {
        isDescending = !other.isDescending;
        tree = other.tree;
    }

    /**
     * Inserts the element into set.
     *
     * @param element value to be inserted
     * @return true if there wasn't this element in, false otherwise
     */
    @Override
    public boolean add(E element) {
        return tree.add(element);
    }

    /**
     * Access to elements of set in some order.
     *
     * @return iterator to the first element
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return (isDescending) ? tree.descendingIterator() : tree.iterator();
    }

    /**
     * Access to size of the set.
     * Constant time.
     *
     * @return number of stored elements
     */
    @Override
    public int size() {
        return tree.size();
    }

    /**
     * Access to elements of set in some order, but reversed comparing to 'iterator()'.
     *
     * @return iterator to last element
     */
    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        return (!isDescending) ? tree.descendingIterator() : tree.iterator();
    }

    /**
     * Access to set with reversed order
     *
     * @return new MyBinaryTreeSet with inverted functions
     */
    @NotNull
    @Override
    public MyBinaryTreeSet<E> descendingSet() {
        return new MyBinaryTreeSet<>(this);
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @return first element according to comparator
     */
    @Override
    public E first() {
        return (isDescending) ? tree.last() : tree.first();
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @return last element according to comparator
     */
    @Override
    public E last() {
        return (!isDescending) ? tree.last() : tree.first();
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is less and not equal to e or null
     */
    @Override
    public E lower(@NotNull E e) {
        return (!isDescending) ? tree.lower(e) : tree.higher(e);
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is less or equal to e or null
     */
    @Override
    public E floor(@NotNull E e) {
        return (!isDescending) ? tree.floor(e) : tree.ceiling(e);
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is greater or equal to e or null
     */
    @Override
    public E ceiling(@NotNull E e) {
        return (isDescending) ? tree.floor(e) : tree.ceiling(e);
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is greater and not equal to e or null
     */
    @Override
    public E higher(@NotNull E e) {
        return (isDescending) ? tree.lower(e) : tree.higher(e);
    }
}