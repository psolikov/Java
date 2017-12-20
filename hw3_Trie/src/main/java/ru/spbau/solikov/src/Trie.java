package ru.spbau.solikov.src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of data structure "trie" that can store strings over some finite alphabet
 */
public class Trie implements Serializable {

    private static final int ALPHABET_SIZE = 26;
    private static final int NUMBER_OF_LETTER_A = 64;

    /**
     * Function to obtain the letter in String from integer
     *
     * @param i number of letter
     * @return string
     */
    private static String getCharForNumber(int i) {
        return i > 0 && i < ALPHABET_SIZE + 1 ? String.valueOf((char) (i + NUMBER_OF_LETTER_A)) : null;
    }

    private static final Map<String, Integer> alphabet;

    static {
        alphabet = new HashMap<String, Integer>();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            alphabet.put(getCharForNumber(i + 1), i);
        }
    }

    /**
     * Implementation of trie's node with static array
     */
    private class TrieNode implements Serializable {
        private boolean isTerminal = false;
        private int size = 0;
        private TrieNode nodes[] = new TrieNode[ALPHABET_SIZE];
    }

    private TrieNode root = new TrieNode();

    /**
     * Function that adds string from letters of alphabet to the trie.
     * False otherwise.
     * Supports size.
     * O(n) time.
     *
     * @param element string to be added
     * @return true if element already in trie, false otherwise
     */
    public boolean add(String element) {
        int indexLastElement = addString(element);

        if (indexLastElement == -1) {
            return false;
        }

        changeSize(indexLastElement, element);

        return true;
    }

    private int addString(String element) {
        TrieNode current = root;
        int index = -1;
        int indexLastElement = 0;

        for (int i = 0; i < element.length(); i++) {
            indexLastElement = i;
            char c = element.charAt(i);
            index = alphabet.get(Character.toString(c));
            if (current.nodes[index] != null) {
                current = current.nodes[index];
                continue;
            }
            current.nodes[index] = new TrieNode();
            current = current.nodes[index];
        }

        if (current.isTerminal) {
            return -1;
        }

        current.isTerminal = true;
        current.size++;

        return indexLastElement;
    }

    private void changeSize(int indexLastElement, String element) {
        TrieNode current = root;
        for (int i = 0; i <= indexLastElement; i++) {
            char c = element.charAt(i);
            int indexCurrent = alphabet.get(Character.toString(c));
            current.size++;
            current = current.nodes[indexCurrent];
        }
    }

    /**
     * Checks if element is in trie.
     * O(n) time, where n is maximum length of stored strings.
     *
     * @param element to be checked
     * @return true if element is in trie, false otherwise
     */
    public boolean contains(String element) {

        TrieNode current = root;

        for (int i = 0; i < element.length(); i++) {
            char c = element.charAt(i);
            int index = alphabet.get(Character.toString(c));
            if (current.nodes[index] != null) {
                current = current.nodes[index];
            } else {
                return false;
            }
        }

        return current.isTerminal;
    }

    /**
     * Removes element from trie. If that element is not prefix of any other element in trie,
     * then deletes all nodes till last terminal vertex from up to down.
     * O(n) time, where n is maximum length of stored strings.
     *
     * @param element to be removed
     * @return true if element was in trie, false otherwise
     */
    public boolean remove(String element) {
        TrieNode current = root;
        TrieNode lastElement = root;
        int indexLastElement = 0;
        int index = -1;

        for (int i = 0; i < element.length(); i++) {
            char c = element.charAt(i);
            index = alphabet.get(Character.toString(c));

            if (current.isTerminal) {
                lastElement = current;
                indexLastElement = i;
            }

            if (current.nodes[index] != null) {
                current = current.nodes[index];
                continue;
            }

            return false;
        }

        return current.isTerminal && recursiveRemove(current, element,
                indexLastElement, lastElement);

    }

    private boolean recursiveRemove(TrieNode current, String element,
                                    int indexLastElement, TrieNode lastElement) {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (current.nodes[i] != null) {
                current.isTerminal = false;
                current = root;
                advanceOnGivenSizeAndReduceSize(element, indexLastElement, current);
                return true;
            }
        }

        removePath(lastElement, element.substring(indexLastElement));

        current = root;
        advanceOnGivenSizeAndReduceSize(element, indexLastElement, current);
        return true;
    }

    private void advanceOnGivenSizeAndReduceSize(String element,
                                                 int indexLastElement, TrieNode current) {
        for (int j = 0; j <= indexLastElement; j++) {
            char c = element.charAt(j);
            int indexCurrent = alphabet.get(Character.toString(c));
            current.size--;
            current = current.nodes[indexCurrent];
        }
    }

    private void removePath(TrieNode node, String element) {

        if (element.length() == 0) {
            return;
        }

        removePath(node.nodes[alphabet.get(Character.toString(element.charAt(0)))], element.substring(1));
        node.nodes[alphabet.get(Character.toString(element.charAt(0)))] = null;

    }

    /**
     * Function for obtaining the Trie's size.
     * O(1) time.
     *
     * @return number of strings stored in trie (is equal to number of terminal vertices)
     */
    public int size() {
        return (root != null) ? root.size : 0;
    }

    /**
     * Output is equal to number of terminal vertices in subtrie of last prefix letter's node.
     * O(n) time, where n is length of prefix.
     *
     * @param prefix to be checked for size
     * @return number of strings started with prefix stored in trie
     */
    public int howManyStartsWithPrefix(String prefix) {

        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = alphabet.get(Character.toString(c));
            if (current.nodes[index] != null) {
                current = current.nodes[index];
            } else {
                return 0;
            }
        }

        return current.size;
    }

    /**
     * Takes output stream and serializes class Trie to that stream with standard Java method
     *
     * @param out stream in what it will write Trie
     * @throws IOException exceptions that could appear while writing
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * Takes input stream and reads class from that stream with standard Java method
     *
     * @param in stream from what it will read information about Trie
     * @throws IOException            exceptions that could appear while reading
     * @throws ClassNotFoundException exception if there's no such file
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = new ObjectInputStream(in);
        Trie other = (Trie) oin.readObject();
        this.root = other.root;
    }
}
