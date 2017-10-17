package ru.spbau.solikov.set.src;

/**
 * Implementation of set structure with binary search tree.
 * Allows to store elements of any type T that could be compared by 'compareTo'.
 *
 * @param <T> type of stored elements
 */
public class MySet<T extends Comparable<T>> {

    private Tree<T> tree = new Tree<>();

    /**
     * Inserts the value into the 'tree' by twitching the Tree.add(T).
     *
     * @param value element to be added
     */
    public void add(T value) {
        tree.add(value);
    }

    /**
     * Access to private storage's size.
     *
     * @return size of tree
     */
    public int size() {
        return tree.size;
    }

    /**
     * Search of element's entry in the set.
     * Twitches the Tree.contains(T)
     *
     * @param value to be checked
     * @return true if it contains the element, false otherwise
     */
    public boolean contains(T value) {
        return tree.contains(value, tree.root);
    }

    /**
     * Structure for collecting unique elements of type T.
     * Has methods for inserting elements, for checking if there is the element and for taking the size of tree.
     * Tree is based on nested class Node.
     *
     * @param <T> type of elements to be added.
     */
    private static class Tree<T extends Comparable<T>> {

        /**
         * Implementation of tree's branch that stores the value of type T and has links to left and right nodes.
         *
         * @param <T>
         */
        private static class Node<T extends Comparable<T>> {

            private T value = null;
            private Node<T> left = null, right = null;

            Node(T value) {
                this.value = value;
            }

            T getValue() {
                return value;
            }

        }

        Node<T> root = null;
        private int size = 0;

        /**
         * A wrapper for 'add(T, Node<t>)'
         *
         * @param value value to be inserted
         */
        void add(T value) {
            root = add(value, root);
        }

        /**
         * Recursive function that checks if the value is in tree.
         *
         * @param value value to be checked
         * @param node  current position in the tree
         * @return true if it contains, false otherwise
         */
        boolean contains(T value, Node<T> node) {
            return node != null && ((node.value.compareTo(value) == 0) || (contains(value, node.left)) || (contains(value, node.right)));
        }

        /**
         * Recursive function that goes down the tree and adds the element to the correct position.
         *
         * @param value value to be added in the tree
         * @param node  current node
         * @return new node that replaces the previous one
         */
        private Node<T> add(T value, Node<T> node) {
            if (node == null) {
                //noinspection unchecked
                node = new Node(value);
                size++;
            } else {
                if (node.getValue() == null) {
                    node = new Node<>(value);
                    size++;
                } else if (value.compareTo(node.value) < 0) {
                    node.left = add(value, node.left);
                } else if (value.compareTo(node.value) > 0) {
                    node.right = add(value, node.right);
                }
            }
            return node;
        }
    }

}
