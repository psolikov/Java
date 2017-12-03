package ru.spbau.solikov.functional.java.src;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class that provides the ability of working with collections and functional interfaces.
 */
public class Collections {
    /**
     * Takes iterable, maps every element of it with function and puts into ArrayList.
     *
     * @param a   values to be mapped
     * @param f   function for mapping
     * @param <A> type of values in a
     * @param <B> type of output ArrayList
     * @return mapped ArrayList
     */
    public static <A, B> AbstractList<B> map(@NotNull final Iterable<A> a,
                                             @NotNull final Function1<? super A, ? extends B> f) {
        ArrayList<B> result = new ArrayList<>();

        for (A element : a) {
            result.add(f.apply(element));
        }

        return result;
    }

    /**
     * Filters the values from iterable by given predicate and puts them into ArrayList.
     *
     * @param a   values to be filtered
     * @param p   predicate for filtering
     * @param <A> type of values in a and ArrayList
     * @return filtered ArrayList
     */
    public static <A> ArrayList<A> filter(@NotNull final Iterable<A> a,
                                          @NotNull final Predicate<A> p) {
        ArrayList<A> result = new ArrayList<>();

        for (A element : a) {
            if (p.apply(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Takes elements from iterable while predicate is false and puts them into ArrayList.
     *
     * @param a   values to be taken from
     * @param p   predicate for filtering
     * @param <A> type of values in a and ArrayList
     * @return filtered ArrayList
     */
    public static <A> ArrayList<A> takeWhile(@NotNull final Iterable<A> a,
                                             @NotNull final Predicate<A> p) {
        ArrayList<A> result = new ArrayList<>();

        for (A element : a) {
            if (!p.apply(element)) {
                result.add(element);
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * Takes elements from iterable while predicate is true and puts them into ArrayList.
     *
     * @param a   values to be taken from
     * @param p   predicate for filtering
     * @param <A> type of values in a and ArrayList
     * @return filtered ArrayList
     */
    public static <A> ArrayList<A> takeUnless(@NotNull final Iterable<A> a,
                                              @NotNull final Predicate<A> p) {
        return takeWhile(a, p.not());
    }

    /**
     * Function that allows to fold the collection from left to right with given function
     * and initial value.
     *
     * @param collection values to be taken from
     * @param y          initial value
     * @param f          folding function of 2 arguments
     * @param <X>        type of the values stored in collection
     * @param <Y>        type of output and initial value
     * @return result of folding
     */
    public static <X, Y> Y foldl(@NotNull final Collection<X> collection, final Y y,
                                 @NotNull final Function2<? super X, ? super Y, ? extends Y> f) {
        Y result = y;

        for (X element : collection) {
            result = f.apply(element, result);
        }

        return result;
    }

    /**
     * Function that allows to fold the collection from right to left with given function
     * and initial value.
     * Calls recursive function-helper.
     *
     * @param collection values to be taken from
     * @param y          initial value
     * @param f          folding function of 2 arguments
     * @param <X>        type of the values stored in collection
     * @param <Y>        type of output and initial value
     * @return result of folding
     */
    public static <X, Y> Y foldr(@NotNull final Collection<X> collection, final Y y,
                                 @NotNull final Function2<? super X, ? super Y, ? extends Y> f) {
        return foldrRec(collection.iterator(), y, f);
    }

    /**
     * Recursive helper for foldr. Iterates through collection and folds it from right to left.
     *
     * @param iterator iterator of given collection to foldr
     * @param y        initial value
     * @param f        folding function of 2 arguments
     * @param <X>      type of the values stored in collection
     * @param <Y>      type of output and initial value
     * @return result of folding
     */
    private static <X, Y> Y foldrRec(@NotNull final Iterator<X> iterator, final Y y,
                                     @NotNull final Function2<? super X, ? super Y, ? extends Y> f) {
        if (iterator.hasNext()) {
            return f.apply(iterator.next(), foldrRec(iterator, y, f));
        } else {
            return y;
        }
    }
}
