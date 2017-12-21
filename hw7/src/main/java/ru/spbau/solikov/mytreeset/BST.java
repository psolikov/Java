package ru.spbau.solikov.mytreeset;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Class that implements the simple binary tree that supports some operations used in MyBinaryTreeSet.
 *
 * @param <E> generic type for stored elements
 */
public class BST<E> {
    private int size = 0;
    private Node<E> root = null;
    @SuppressWarnings("unchecked")
    private Comparator<? super E> comparator = (E first, E second)
            -> ((Comparable<? super E>) first).compareTo(second);

    /**
     * Constructor without arguments means that set will be sorting elements by natural order.
     */
    public BST() {
    }

    /**
     * Allows to change the comparator.
     *
     * @param comparator to be used as sorting comparator of stored values
     */
    public BST(@NotNull Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Inserts the element into set.
     *
     * @param element value to be inserted
     * @return true if there wasn't this element in, false otherwise
     */
    public boolean add(@NotNull E element) {
        if (root == null) {
            root = new Node<>(element);
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

    private boolean add(@NotNull Node<E> node, @NotNull E element) {
        if (comparator.compare(node.value, element) == 0) {
            return true;
        }

        if (comparator.compare(node.value, element) > 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                node.left.parent = node;
                return false;
            }

            return add(node.left, element);
        } else {
            if (node.right == null) {
                node.right = new Node<>(element);
                node.right.parent = node;
                return false;
            }

            return add(node.right, element);
        }
    }

    /**
     * Access to size of the tree.
     * Constant time.
     *
     * @return number of stored elements
     */
    public int size() {
        return size;
    }

    /**
     * Access to elements of tree in some order.
     *
     * @return iterator to the first element
     */
    @NotNull
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            E nextValue;

            {
                if (root == null) {
                    nextValue = null;
                } else {
                    Node<E> current;
                    current = root;
                    nextValue = current.value;
                    while (current.left != null) {
                        current = current.left;
                        nextValue = current.value;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return nextValue != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }

                E returningValue = nextValue;

                nextValue = higher(returningValue);

                return returningValue;
            }
        };
    }

    /**
     * Worst time is linear of number of nodes in tree.
     *
     * @param e element to be compared
     * @return closest element that is greater and not equal to e or null
     */
    public E higher(@NotNull E e) {
        return root == null ? null : higher(root, e);
    }

    private E higher(@NotNull Node<E> node, @NotNull E e) {
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
     * Access to elements of tree in some order, but reversed comparing to 'iterator()'.
     *
     * @return iterator to last element
     */
    @NotNull
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            E nextValue;

            {
                if (root == null) {
                    nextValue = null;
                } else {
                    Node<E> current;
                    current = root;
                    nextValue = current.value;
                    while (current.right != null) {
                        current = current.right;
                        nextValue = current.value;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return nextValue != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }

                E returningValue = nextValue;

                nextValue = lower(returningValue);

                return returningValue;
            }
        };
    }

    /**
     * Worst time is linear of number of nodes in tree.
     *
     * @param e element to be compared
     * @return closest element that is less and not equal to e or null
     */
    public E lower(@NotNull E e) {
        return root == null ? null : lower(root, e);
    }

    private E lower(@NotNull Node<E> node, @NotNull E e) {
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
     * Worst time is linear of number of nodes in tree.
     *
     * @param e element to be compared
     * @return closest element that is greater or equal to e or null
     */
    public E ceiling(@NotNull E e) {
        return root == null ? null : ceiling(root, e);
    }

    private E ceiling(@NotNull Node<E> node, @NotNull E e) {
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
     * Worst time is linear of number of nodes in tree.
     *
     * @param e element to be compared
     * @return closest element that is less or equal to e or null
     */
    public E floor(@NotNull E e) {
        return root == null ? null : floor(root, e);
    }

    private E floor(@NotNull Node<E> node, @NotNull E e) {
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
     * Worst time is linear of number of nodes in tree.
     *
     * @return first element according to comparator
     */
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
     * Worst time is linear of number of nodes in tree.
     *
     * @return last element according to comparator
     */
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
     * Nested class that implements the node of tree.
     *
     * @param <E> type of stored elements
     */
    private static class Node<E> {
        private Node<E> parent = null;
        private Node<E> left = null;
        private Node<E> right = null;
        private E value;

        /**
         * Constructs new node with given value.
         *
         * @param value to be stored in Node
         */
        Node(E value) {
            this.value = value;
        }
    }
}