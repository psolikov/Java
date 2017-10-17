import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.Request;
import ru.spbau.solikov.maybe.src.Maybe;
import ru.spbau.solikov.maybe.src.MaybeException;

import javax.swing.text.rtf.RTFEditorKit;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Test class for class Maybe. Tries to make and operate exemplars with different type. Also tires to read from file some Integers,
 * square them and write them to output file through Maybe instances.
 * Input file: Maybe.in
 * Output file: Maybe.out
 */
public class MaybeTest {

    @Test
    public void testConstructor() {
        Maybe<Integer> maybe = new Maybe<>(1);
        assertEquals(true, maybe.isPresent());
    }

    @Test
    public void testJust() {
        Maybe<Integer> maybe = Maybe.just(564);
        assertEquals(true, maybe.isPresent());
    }

    @Test
    public void testNothing() {
        Maybe<String> maybe = Maybe.nothing();
        assertEquals(false, maybe.isPresent());
    }

    @Test(expected = MaybeException.class)
    public void testGetOnEmpty() throws MaybeException {
        Maybe<Integer> maybe = Maybe.nothing();
        maybe.get();
    }

    @Test
    public void testGetOnNonEmpty() {
        Maybe<Integer> maybe = Maybe.just(564);
        try {
            assertEquals(new Integer(564), maybe.get());
        } catch (MaybeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetOnNonEmptyString() {
        Maybe<String> maybe = Maybe.just("May be");
        try {
            assertEquals("May be", maybe.get());
        } catch (MaybeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsPresentOnEmpty() {
        Maybe<Character> maybe = Maybe.nothing();
        assertEquals(false, maybe.isPresent());
    }

    @Test
    public void testIsPresentOnNonEmpty() {
        Maybe<RTFEditorKit> maybe = Maybe.just(new RTFEditorKit());
        assertEquals(true, maybe.isPresent());
    }

    @Test
    public void testMapOnEmpty() {
        assertEquals(false, Maybe.nothing().map(x -> x).isPresent());
    }

    @Test
    public void testMapOnNonEmpty() {
        try {
            assertEquals(new Integer(565), Maybe.just(1).map(x -> x + 564).get());
        } catch (MaybeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testReadFromInputFileAndWriteToOutputFile() {
        try {

            Scanner sc = new Scanner(new File("Maybe.in"));
            FileWriter fw = new FileWriter("Maybe.out");
            BufferedWriter w = new BufferedWriter(fw);
            ArrayList<Maybe<Integer>> al = new ArrayList<>();

            while (sc.hasNextLine()) {

                String number = sc.nextLine();
                Maybe<Integer> maybe;

                try {
                    int n = Integer.parseInt(number);
                    maybe = Maybe.just(n);
                } catch (NumberFormatException e) {
                    maybe = new Maybe<>();
                }

                al.add(maybe.map(x -> x * x));

            }

            for (Maybe<Integer> mb : al) {

                if (mb.isPresent()) {
                    w.write(String.valueOf(mb.get()));
                    w.write("\n");
                } else {
                    w.write("null\n");
                }

            }

            sc.close();
            w.flush();
            w.close();

        } catch (IOException | MaybeException e) {
            fail(e.getMessage());
        }
    }
}
