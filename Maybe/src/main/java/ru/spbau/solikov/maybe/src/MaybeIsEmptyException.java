package ru.spbau.solikov.maybe.src;

import org.jetbrains.annotations.NotNull;

/**
 * An exception needed from class Maybe.
 * Throws when try to get instance of object of class Maybe without any value stored.
 */
public class MaybeIsEmptyException extends Exception {

    MaybeIsEmptyException(@NotNull String message) {
        super(message);
    }

}
