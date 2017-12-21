import org.junit.Before;
import org.junit.Test;
import ru.spbau.solikov.mytreeset.MyBinaryTreeSet;
import ru.spbau.solikov.mytreeset.MyTreeSet;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Class for testing the implementation of MyTreeSet interface - MyBinaryTreeSet.
 */
public class MyBinaryTreeSetTest {
    private MyBinaryTreeSet<Integer> intTreeSet;

    @Before
    public void setUp() {
        intTreeSet = new MyBinaryTreeSet<>();
    }

    @Test
    public void testAddToEmpty() {
        assertEquals(true, intTreeSet.add(1));
    }

    @Test
    public void testAddTheSameValue() {
        intTreeSet.add(1);
        assertEquals(false, intTreeSet.add(1));
    }

    @Test
    public void testSizeOfEmpty() {
        assertEquals(0, intTreeSet.size());
    }

    @Test
    public void testSizeAfterAddingOne() {
        intTreeSet.add(1);
        assertEquals(1, intTreeSet.size());
    }

    @Test
    public void testSizeAfterMultipleAdding() {
        for (int i = 0; i < 10; i++) {
            intTreeSet.add(i);
        }

        assertEquals(10, intTreeSet.size());
    }

    @Test
    public void testIteratorFromEmptySet() {
        Iterator<Integer> iterator = intTreeSet.iterator();
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorFromNonEmptySet() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }

        Iterator<Integer> iterator = intTreeSet.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(0, iterator.next().intValue());
        assertEquals(1, iterator.next().intValue());
        assertEquals(2, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
        assertEquals(4, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(6, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(8, iterator.next().intValue());
        assertEquals(9, iterator.next().intValue());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testDescendingIteratorFromEmptySet() {
        Iterator<Integer> iterator = intTreeSet.descendingIterator();
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testDescendingIteratorFromNonEmptySet() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }

        Iterator<Integer> iterator = intTreeSet.descendingIterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(9, iterator.next().intValue());
        assertEquals(8, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(6, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(4, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
        assertEquals(2, iterator.next().intValue());
        assertEquals(1, iterator.next().intValue());
        assertEquals(0, iterator.next().intValue());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testDescendingSetOfEmptySet() {
        assertEquals(0, intTreeSet.descendingSet().size());
    }

    @Test
    public void testSizeOfDescendingSetOfNonEmptySet() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(10, intTreeSet.descendingSet().size());
    }

    @Test
    public void testFirstEmptySet() {
        assertEquals(null, intTreeSet.first());
    }

    @Test
    public void testFirstNonEmptySet() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(0, intTreeSet.first().intValue());
    }

    @Test
    public void testLastEmptySet() {
        assertEquals(null, intTreeSet.last());
    }

    @Test
    public void testLastNonEmptySet() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(9, intTreeSet.last().intValue());
    }

    @Test
    public void testLowerEmptySet() {
        assertEquals(null, intTreeSet.lower(1));
    }

    @Test
    public void testLowerNonEmptySetWithMin() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(null, intTreeSet.lower(0));
    }

    @Test
    public void testLowerNonEmptySetWithMax() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(8, intTreeSet.lower(9).intValue());
    }

    @Test
    public void testHigherEmptySet() {
        assertEquals(null, intTreeSet.lower(1));
    }

    @Test
    public void testHigherNonEmptySetWithMax() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(null, intTreeSet.higher(9));
    }

    @Test
    public void testHigherNonEmptySetWithMin() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(1, intTreeSet.higher(0).intValue());
    }

    @Test
    public void testFloorEmptySet() {
        assertEquals(null, intTreeSet.floor(1));
    }

    @Test
    public void testFloorNonEmptySetWithFakeValue() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(9, intTreeSet.floor(100).intValue());
    }

    @Test
    public void testFloorNonEmptySetWithExistingValue() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(7, intTreeSet.floor(7).intValue());
    }

    @Test
    public void testCeilingEmptySet() {
        assertEquals(null, intTreeSet.ceiling(1));
    }

    @Test
    public void testCeilingNonEmptySetWithFakeValue() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(0, intTreeSet.ceiling(-1).intValue());
    }

    @Test
    public void testCeilingNonEmptySetWithExistingValue() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }
        assertEquals(7, intTreeSet.ceiling(7).intValue());
    }

    @Test
    public void testDescendingSetFunctions() {
        for (int i = 9; i > 4; i--) {
            intTreeSet.add(i);
        }
        for (int i = 0; i < 5; i++) {
            intTreeSet.add(i);
        }

        MyTreeSet<Integer> intTreeSetDescending = intTreeSet.descendingSet();

        assertEquals(intTreeSetDescending.ceiling(4), intTreeSet.floor(4));
        assertEquals(intTreeSetDescending.floor(10), intTreeSet.ceiling(10));
        assertEquals(intTreeSetDescending.last(), intTreeSet.first());
        assertEquals(intTreeSetDescending.first(), intTreeSet.last());
        assertEquals(intTreeSetDescending.lower(2), intTreeSet.higher(2));
        assertEquals(intTreeSetDescending.higher(8), intTreeSet.lower(8));

        intTreeSetDescending.add(100);
        intTreeSetDescending.add(-100);

        assertEquals(intTreeSetDescending.ceiling(4), intTreeSet.floor(4));
        assertEquals(intTreeSetDescending.floor(10), intTreeSet.ceiling(10));
        assertEquals(intTreeSetDescending.last(), intTreeSet.first());
        assertEquals(intTreeSetDescending.first(), intTreeSet.last());
        assertEquals(intTreeSetDescending.lower(2), intTreeSet.higher(2));
    }

    @Test
    public void testComparator(){
        Comparator<String> cmp = Comparator.comparing(String::valueOf);
        MyBinaryTreeSet<String> strings = new MyBinaryTreeSet<>(cmp);

        strings.add("Wed15Nov");
        strings.add("SPbAU");
        strings.add("SPbSU");
        strings.add("JAVA");
        strings.add("Deadline");

        assertEquals(5, strings.size());
        assertEquals(strings.ceiling("Saint-Petersburg"), "Wed15Nov");
        assertEquals(strings.floor("Saint-Petersburg"), "SPbSU");
        assertEquals(strings.last(), "Wed15Nov");
        assertEquals(strings.first(), "Deadline");
        assertEquals(strings.lower("Meh"), "JAVA");
        assertEquals(strings.higher("MIT"), "SPbAU");
    }
}
