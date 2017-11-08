package ru.spbau.solikov.functional.java.src;

import org.jetbrains.annotations.NotNull;

/**
 * A functional interface that represents single argument function.
 *
 * @param <X> type of an argument, i.e. the domain of a function
 * @param <Y> type of a returning value, i.e. the range of a function
 */
@FunctionalInterface
public interface Function1<X, Y> {
    /**
     * Applies the instance to given variable.
     *
     * @param x variable to be applied
     * @return output of a function
     */
    Y apply(X x);

    /**
     * Composition of the instance and a given function in the natural order.
     *
     * @param g   function from <Y> to <Z> to be composed
     * @param <Z> range of the composition
     * @return new function from <X> to <Z> that represents composition
     */
    default <Z> Function1<X, Z> compose(@NotNull Function1<? super Y, ? extends Z> g) {
        return (X x) -> g.apply(apply(x));
    }
}
