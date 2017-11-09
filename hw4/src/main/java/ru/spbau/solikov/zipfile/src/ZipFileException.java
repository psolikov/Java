package ru.spbau.solikov.zipfile.src;

/**
 * An exception that is used for throwing in MyZipFile.
 */
public class ZipFileException extends Exception {
    public ZipFileException(String message){
        super(message);
    }
}