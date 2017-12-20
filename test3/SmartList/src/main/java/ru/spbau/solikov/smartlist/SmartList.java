package ru.spbau.solikov.smartlist;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of SmartList that is optimized for little data.
 *
 * @param <E> type of values to be stored
 */
public class SmartList<E> extends AbstractList<E> implements List<E> {

    private int size = 0;
    private Object elements = null;

    /**
     * Simple constructor for empty SmartList
     */
    public SmartList() {
    }

    /**
     * Constructor that generates empty SmartList and fills it with elements from collection
     *
     * @param collection elements to be inserted
     */
    public SmartList(@NotNull Collection<E> collection) {
        addAll(collection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E element) {
        if (size == 0) {
            elements = element;
        }

        if (size == 1) {
            E storedElement = (E) elements;
            elements = new Object[5];
            ((Object[]) elements)[0] = storedElement;
            ((Object[]) elements)[1] = element;
        }

        if (2 <= size && size < 5) {
            ((Object[]) elements)[size] = element;
        }

        if (size == 5) {
            ArrayList<Object> arrayList = new ArrayList<>();
            Object[] storedValues = (Object[]) elements;
            Collections.addAll(arrayList, storedValues);
            elements = arrayList;
        }

        if (size > 5) {
            ((ArrayList) elements).add(element);
        }

        size++;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        size = 0;
        elements = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 0) {
            return null;
        }

        if (size == 1) {
            E storedValue = (E) elements;
            elements = null;
            size--;

            return storedValue;
        }

        if (size <= 5) {
            Object[] storedValues = (Object[]) elements;
            Object value = ((Object[]) elements)[index];
            storedValues[index] = null;

            System.arraycopy(elements, index + 1, elements, index, size - (index + 1));

            if (size == 2) {
                elements = storedValues[0];
            }

            size--;

            return (E) value;
        }

        ArrayList storedElements = (ArrayList) elements;
        Object value = storedElements.remove(index);

        if (size == 6) {
            elements = storedElements.toArray();
        }

        size--;

        return (E) value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            E storedValue = (E) elements;
            elements = element;

            return storedValue;
        }

        if (2 <= size && size <= 5) {
            E storedValue = (E) ((Object[]) elements)[index];
            ((Object[]) elements)[index] = element;

            return storedValue;
        }

        E storedValue = (E) ((ArrayList) elements).get(index);
        ((ArrayList<Object>) elements).set(index, element);

        return storedValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1 & index == 0) {
            return (E) elements;
        }
        if (2 <= size && size <= 5) {
            return (E) ((Object[]) elements)[index];
        }

        return (E) (((ArrayList) elements).get(index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }
}
