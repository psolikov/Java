package ru.spbau.solikov.maybe.src;

import java.util.function.Function;

/**
 * Generic implementation of class with single variable of type T and some methods that operate with it.
 *
 * @param <T> the type of value
 */
public class Maybe<T> {

    private T element = null;

    public Maybe() {
    }

    public Maybe(T t) {
        element = t;
    }

    /**
     * Method for taking the Maybe's instance.
     *
     * @return value that is stored of type T
     * @throws MaybeException could be thrown if it's empty
     */
    public T get() throws MaybeException {
        if (element != null) {
            return element;
        } else {
            throw new MaybeException("No element stored");
        }
    }

    /**
     * Checks if class actually stores something
     *
     * @return true if stores, false otherwise
     */
    public boolean isPresent() {
        return element != null;
    }

    /**
     * Static method for getting an instance of just created object with taken value.
     *
     * @param t   value to be stored
     * @param <T> type of value to be stored
     * @return the object that was just created
     */
    public static <T> Maybe<T> just(T t) {
        return new Maybe<>(t);
    }

    /**
     * Static method for getting an instance of just created object without any value stored in.
     *
     * @param <T> type of object
     * @return the object that was just created
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<>();
    }

    /**
     * Method that takes function and applies it to stored value
     *
     * @param mapper a function to be mapped with type from T to U
     * @param <U>    new type of mapped value
     * @return new object with mapped value or object with null stored  
     */
    public <U> Maybe<U> map(Function<T, U> mapper) {
        if (element != null) {
            return new Maybe<U>(mapper.apply(element));
        } else {
            return new Maybe<U>();
        }
    }

}
