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
    private int size = 0;
    private Node<E> root = null;
    private Comparator<? super E> comparator = (E first, E second)
            -> ((Comparable<? super E>) first).compareTo(second);

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
        this.comparator = comparator;
    }

    /**
     * Inserts the element into set.
     *
     * @param element value to be inserted
     * @return true if there wasn't this element in, false otherwise
     */
    @Override
    public boolean add(E element) {
        if (root == null) {
            root = new Node<E>(element);
            size = 1;
            return true;
        } else {
            boolean wasInTree;
            if (!(wasInTree = add(root, element))) {
                size++;
            }

            return !wasInTree;
        }
    }

    private boolean add(@NotNull Node<E> node, E element) {
        if (comparator.compare(node.value, element) == 0) {
            return true;
        }

        if (comparator.compare(node.value, element) > 0) {
            if (node.left == null) {
                node.left = new Node<E>(element);
                node.left.parent = node;
                return false;
            }

            return add(node.left, element);
        } else {
            if (node.right == null) {
                node.right = new Node<E>(element);
                node.right.parent = node;
                return false;
            }

            return add(node.right, element);
        }
    }

    /**
     * Access to elements of set in some order.
     *
     * @return iterator to the first element
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current;

            {
                if (root == null) {
                    current = null;
                } else {
                    current = root;
                    while (current.left != null) {
                        current = current.left;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }

                E returningValue = current.value;

                if (current.right != null) {
                    current = current.right;
                    while (current.left != null) {
                        current = current.left;
                    }
                } else {
                    while (current.parent != null &&
                            comparator.compare(current.parent.value, current.value) < 0) {
                        current = current.parent;
                    }
                    current = current.parent;
                }

                return returningValue;
            }
        };
    }

    /**
     * Access to size of the set.
     * Constant time.
     *
     * @return number of stored elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Access to elements of set in some order, but reversed comparing to 'iterator()'.
     *
     * @return iterator to last element
     */
    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            Node<E> current;

            {
                if (root == null) {
                    current = null;
                } else {
                    current = root;
                    while (current.right != null) {
                        current = current.right;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }

                E returningValue = current.value;

                if (current.left != null) {
                    current = current.left;
                    while (current.right != null) {
                        current = current.right;
                    }
                } else {
                    while (current.parent != null &&
                            comparator.compare(current.parent.value, current.value) > 0) {
                        current = current.parent;
                    }
                    current = current.parent;
                }

                return returningValue;
            }
        };
    }

    /**
     * Access to set with reversed order
     *
     * @return new MyBinaryTreeSet with inverted functions
     */
    @Override
    public MyBinaryTreeSet<E> descendingSet() {
        return new DescendingMyBinaryTreeSet();
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @return first element according to comparator
     */
    @Override
    public E first() {
        if (root == null) {
            return null;
        }

        Node<E> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @return last element according to comparator
     */
    @Override
    public E last() {
        if (root == null) {
            return null;
        }

        Node<E> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is less and not equal to e or null
     */
    @Override
    public E lower(E e) {
        return root == null ? null : lower(root, e);
    }

    private E lower(@NotNull Node<E> node, E e) {
        if (comparator.compare(e, node.value) <= 0) {
            if (node.left == null) {
                return null;
            }

            return lower(node.left, e);
        } else {
            if (node.right == null) {
                return node.value;
            }

            E lower = lower(node.right, e);

            if (lower == null) {
                return node.value;
            }

            return lower;
        }
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is less or equal to e or null
     */
    @Override
    public E floor(E e) {
        return root == null ? null : floor(root, e);
    }

    private E floor(@NotNull Node<E> node, E e) {
        if (comparator.compare(node.value, e) > 0) {
            return node.left == null ? null : floor(node.left, e);
        } else {
            if (node.value == e) {
                return e;
            }

            if (node.right == null) {
                return node.value;
            }

            E floor = floor(node.right, e);

            return floor == null ? node.value : floor;
        }
    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is greater or equal to e or null
     */
    @Override
    public E ceiling(E e) {
        return root == null ? null : ceiling(root, e);
    }

    private E ceiling(@NotNull Node<E> node, E e) {
        if (comparator.compare(node.value, e) == 0) {
            return node.value;
        }

        if (comparator.compare(node.value, e) > 0) {
            if (node.left == null) {
                return node.value;
            }

            E ceiling = ceiling(node.left, e);

            return ceiling == null ? node.value : ceiling;
        } else {
            return node.right == null ? null : ceiling(node.right, e);
        }

    }

    /**
     * Worst time is linear of number of nodes in set.
     *
     * @param e element to be compared
     * @return closest element that is greater and not equal to e or null
     */
    @Override
    public E higher(E e) {
        return root == null ? null : higher(root, e);
    }

    private E higher(@NotNull Node<E> node, E e) {
        if (!(comparator.compare(node.value, e) > 0)) {
            return node.right == null ? null : higher(node.right, e);
        } else {
            if (node.left == null) {
                return comparator.compare(node.value, e) == 0 ? null : node.value;
            }

            E higher = higher(node.left, e);

            if (higher == null) {
                return comparator.compare(node.value, e) == 0 ? null : node.value;
            }

            return higher;
        }
    }

    /**
     * Nested class that implements the node of tree.
     *
     * @param <E> type of stored elements
     */
    private static class Node<E> {
        private Node<E> parent = null;
        private Node<E> left = null;
        private Node<E> right = null;
        private E value;

        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Class that represents the set in which elements are stored in the opposite order.
     */
    private class DescendingMyBinaryTreeSet extends MyBinaryTreeSet<E> implements MyTreeSet<E> {

        /**
         * Access to elements of parent set in reversed order.
         *
         * @return iterator to last element of parent set
         */
        @NotNull
        @Override
        public Iterator<E> iterator() {
            return MyBinaryTreeSet.this.descendingIterator();
        }

        /**
         * Size of parent and descending sets.
         * Constant time.
         *
         * @return size of parent set
         */
        @Override
        public int size() {
            return MyBinaryTreeSet.this.size;
        }

        /**
         * Access to elements of parent order in its initial order.
         *
         * @return iterator to begin of parent set
         */
        @NotNull
        @Override
        public Iterator<E> descendingIterator() {
            return MyBinaryTreeSet.this.iterator();
        }

        /**
         * Access to the set with initial order.
         *
         * @return parent set
         */
        @Override
        public MyBinaryTreeSet<E> descendingSet() {
            return MyBinaryTreeSet.this;
        }

        /**
         * First element of descending set.
         *
         * @return last element of parent set
         */
        @Override
        public E first() {
            return MyBinaryTreeSet.this.last();
        }

        /**
         * Last element of descending set.
         *
         * @return first element of parent set
         */
        @Override
        public E last() {
            return MyBinaryTreeSet.this.first();
        }

        /**
         * Lower element of descending set.
         *
         * @return higher element of parent set
         */
        @Override
        public E lower(E e) {
            return MyBinaryTreeSet.this.higher(e);
        }

        /**
         * Floor of given element of descending set.
         *
         * @return ceiling of given element of parent set
         */
        @Override
        public E floor(E e) {
            return MyBinaryTreeSet.this.ceiling(e);
        }

        /**
         * Ceiling of given element of descending set.
         *
         * @return floor of given element of parent set
         */
        @Override
        public E ceiling(E e) {
            return MyBinaryTreeSet.this.floor(e);
        }

        /**
         * Higher element of descending set.
         *
         * @return lower element of parent set
         */
        @Override
        public E higher(E e) {
            return MyBinaryTreeSet.this.lower(e);
        }
    }
}