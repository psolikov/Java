package ru.spbau.solikov.functional.java.src;

import org.jetbrains.annotations.NotNull;

/**
 * A functional interface that represents double argument function.
 *
 * @param <X> type of a first argument, i.e. the domain of a function
 * @param <Y> type of a second argument, i.e. the domain of a function
 * @param <Z> type of a returning value, i.e. the range of a function
 */
@FunctionalInterface
public interface Function2<X, Y, Z> {
    /**
     * Applies the instance to given variables.
     *
     * @param x variable of the first type to be applied
     * @param y variable of the second type to be applied
     * @return output of a function
     */
    Z apply(final X x, final Y y);

    /**
     * Composition of the instance and a given single argument function in the natural order.
     *
     * @param g   function from <Z> to <D> to be composed
     * @param <D> range of the composition
     * @return new function from cartesian product of <X> and <Y> to <D> that represents composition
     */
    default <D> Function2<X, Y, D> compose(@NotNull final Function1<? super Z, ? extends D> g) {
        return (final X x, final Y y) -> g.apply(apply(x, y));
    }

    /**
     * Binds the first argument with given value
     *
     * @param x value to insert into the first argument
     * @return single valued function with inserted value
     */
    default Function1<Y, Z> bind1(final X x) {
        return (final Y y) -> apply(x, y);
    }

    /**
     * Binds the second argument with given value
     *
     * @param y value to insert into the second argument
     * @return single valued function with inserted value
     */
    default Function1<X, Z> bind2(final Y y) {
        return (final X x) -> apply(x, y);
    }

    /**
     * Partial application of double argument function
     *
     * @param y value to insert into the second argument
     * @return single valued function with inserted value
     */
    default Function1<X, Z> curry(final Y y) {
        return bind2(y);
    }
}
