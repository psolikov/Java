package ru.spbau.solikov.maybe.src;

/**
 * An exception needed from class Maybe.
 * Throws when try to get instance of object of class Maybe without any value stored.
 */
public class MaybeException extends Exception {

    MaybeException(String message) {
        super(message);
    }

}
