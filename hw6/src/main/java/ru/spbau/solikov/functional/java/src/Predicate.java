package ru.spbau.solikov.functional.java.src;

import org.jetbrains.annotations.NotNull;

/**
 * Functional interface that represents single argument predicate function that returns Boolean.
 * Extends single argument functional interface.
 *
 * @param <X> type of a argument, i.e. the domain of a function
 */
@FunctionalInterface
public interface Predicate<X> extends Function1<X, Boolean>{
    /**
     * The negation of instance predicate.
     *
     * @return new predicate with reversed output
     */
    default Predicate<X> not(){
        return (X x) -> !apply(x);
    }

    /**
     * The conjunction of instance and given predicate.
     *
     * @param other predicate to conjunct with
     * @return new predicate that represents the conjunction
     */
    default Predicate<X> and(@NotNull Predicate<? super X> other){
        return (X x) -> apply(x) && other.apply(x);
    }

    /**
     * The disjunction of instance and given predicate.
     *
     * @param other predicate to make disjunction with
     * @return new predicate that represents the disjunction
     */
    default Predicate<X> or(@NotNull Predicate<? super X> other){
        return (X x) -> apply(x) || other.apply(x);
    }

    /**
     * Static function that gives the predicate that is always true.
     *
     * @return true
     */
    static Predicate<Object> ALWAYS_TRUE(){
        return (x) -> true;
    }

    /**
     * Static function that gives the predicate that is always false.
     *
     * @return false
     */
    static Predicate<Object> ALWAYS_FALSE(){
        return (x) -> false;
    }
}
