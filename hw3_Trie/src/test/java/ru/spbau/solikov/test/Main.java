package ru.spbau.solikov.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.src.Trie;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;

/**
 * Class for testing methods from "Trie" class. Creates a file for testing serialize and deserialize and deletes it after tests.
 */
public class Main {

    Trie trie;

    @Before
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testIfAddOneElement() {
        assertEquals(true, trie.add("A"));
    }

    @Test
    public void testIfAddOneSizeReturnsOne() {
        trie.add("A");
        assertEquals(1, trie.size());
    }

    @Test
    public void testContainsAfterAddOne() {
        trie.add("ASDFGTYMVNCXMKENRBVEK");
        assertEquals(true, trie.contains("ASDFGTYMVNCXMKENRBVEK"));
    }

    @Test
    public void testAddTheSameOneElement() {
        trie.add("A");
        assertEquals(false, trie.add("A"));
    }

    @Test
    public void testSizeAfterAddingTheSameOneElement() {
        trie.add("A");
        trie.add("A");
        assertEquals(1, trie.size());
    }

    @Test
    public void testAddLotsElements() {
        trie.add("A");
        assertEquals(true, trie.add("B"));
    }

    @Test
    public void testSizeAfterAddLotsElements() {
        trie.add("A");
        trie.add("B");
        trie.add("SPBAU");
        assertEquals(3, trie.size());
    }

    @Test
    public void testContainsAfterAddLotsElements() {
        trie.add("A");
        trie.add("B");
        trie.add("SPBAU");
        assertEquals(true, trie.contains("SPBAU"));
    }

    @Test
    public void testDoesNotContainAfterAddLotsElements() {
        trie.add("A");
        trie.add("B");
        trie.add("SPBAU");
        assertEquals(false, trie.contains("SPBSU"));
    }

    @Test
    public void testRemoveFromOneElementTrie() {
        trie.add("A");
        assertEquals(true, trie.remove("A"));
    }

    @Test
    public void testFalseRemoveFromOneElementTrie() {
        trie.add("A");
        assertEquals(false, trie.remove("ABBA"));
    }

    @Test
    public void testRemoveFromLotsElementTrie() {
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        for (int i = 1; i < 11; i++) {
            assertEquals(true, (trie.remove(String.valueOf((char) (i + 64)))));
        }
    }

    @Test
    public void testSizeAfterLotsOfAdds() {
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        assertEquals(10, trie.size());
    }

    @Test
    public void testSizeAfterRemovingFromLotsElementTrie() {
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        trie.remove("B");
        assertEquals(9, trie.size());
    }

    @Test
    public void testSizeOfZeroTrie() {
        assertEquals(0, trie.size());
    }

    @Test
    public void testHowManyStartsWithPrefixOfZeroTable() {
        assertEquals(0, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void testHowManyStartsWithPrefixOfOneElementTable() {
        trie.add("A");
        assertEquals(1, trie.howManyStartsWithPrefix("A"));
    }

    @Test
    public void testFalseHowManyStartsWithPrefixOfOneElementTable() {
        trie.add("A");
        assertEquals(0, trie.howManyStartsWithPrefix("B"));
    }

    @Test
    public void testHowManyStartsWithPrefixOfLotsElementTable() {
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        assertEquals(1, trie.howManyStartsWithPrefix("A"));
    }

    @Test
    public void testManyStartsWithPrefix() {
        trie.add("A");
        trie.add("AB");
        trie.add("BA");
        trie.add("ABC");
        trie.add("ABCD");
        trie.add("ABCDE");
        assertEquals(5, trie.howManyStartsWithPrefix("A"));
    }

    @Test
    public void testSerializeEmpty() throws IOException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        trie.serialize(fos);
    }

    @Test
    public void testDeserializeEmpty() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        FileInputStream fis = new FileInputStream("test.trie");
        trie.serialize(fos);
        trie.deserialize(fis);
    }

    @Test
    public void testSerializeLotsElements() throws IOException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        trie.serialize(fos);
    }

    @Test
    public void testDeserializeLotsElements() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        FileInputStream fis = new FileInputStream("test.trie");
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        trie.serialize(fos);
        trie.deserialize(fis);
    }

    @Test
    public void testSizeAfterDeserializeLotsElements() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        FileInputStream fis = new FileInputStream("test.trie");
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        trie.serialize(fos);
        Trie other = new Trie();
        other.deserialize(fis);
        assertEquals(true, other.size() == trie.size());
    }

    @Test
    public void testContaintsAfterDeserializeLotsElements() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("test.trie");
        FileInputStream fis = new FileInputStream("test.trie");
        for (int i = 1; i < 11; i++) {
            trie.add(String.valueOf((char) (i + 64)));
        }
        trie.serialize(fos);
        Trie other = new Trie();
        other.deserialize(fis);
        assertEquals(true, other.contains("A"));
    }

    @After
    public void rmDir() {
        try {
            Path path = FileSystems.getDefault().getPath("test.trie");
            Files.delete(path);
        } catch (NoSuchFileException x) {
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", "test.trie");
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
